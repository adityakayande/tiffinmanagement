package com.example.tiffin.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiffin.R
import com.example.tiffin.TiffinApp
import com.example.tiffin.databinding.FragmentCustomerDetailsBinding
import com.example.tiffin.ui.adapters.AbsenceAdapter
import com.example.tiffin.ui.dialogs.CustomerDialogFragment
import com.example.tiffin.ui.dialogs.CustomerDialogResult
import com.example.tiffin.ui.viewmodel.CustomerDetailsViewModel
import com.example.tiffin.ui.viewmodel.CustomerDetailsViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import android.net.Uri
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CustomerDetailsFragment : Fragment() {

    private var binding: FragmentCustomerDetailsBinding? = null

    private val bindingNonNull: FragmentCustomerDetailsBinding
        get() = requireNotNull(binding)

    private val customerId: Long by lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().getLong("customerId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerDetailsBinding.inflate(inflater, container, false)
        return bindingNonNull.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as TiffinApp
        val viewModel = androidx.lifecycle.ViewModelProvider(
            this,
            CustomerDetailsViewModelFactory(
                customerId = customerId,
                customerRepository = app.customerRepository,
                paymentRepository = app.paymentRepository,
                absenceRepository = app.absenceRepository
            )
        )[CustomerDetailsViewModel::class.java]

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        // ── Customer info + billing ──────────────────────────────────────────
        viewLifecycleOwner.lifecycleScope.launch {
            kotlinx.coroutines.flow.combine(
                viewModel.customer,
                viewModel.totalDue,
                viewModel.currentMonthPayment,
                viewModel.previousDues
            ) { customer, totalDue, currentPayment, prevDues ->
                data class UiData(
                    val customer: com.example.tiffin.data.CustomerEntity?,
                    val totalDue: Long,
                    val currentPayment: com.example.tiffin.data.PaymentEntity?,
                    val previousDues: Long
                )
                UiData(customer, totalDue, currentPayment, prevDues)
            }.collect { data ->
                val customer = data.customer ?: return@collect
                
                bindingNonNull.textCustomerName.text = customer.name
                bindingNonNull.textCustomerPhone.text = customer.phone
                if (!customer.address.isNullOrBlank()) {
                    bindingNonNull.textCustomerAddress.visibility = View.VISIBLE
                    bindingNonNull.textCustomerAddress.text = customer.address
                } else {
                    bindingNonNull.textCustomerAddress.visibility = View.GONE
                }
                bindingNonNull.textCustomerJoinDate.text =
                    "Joined: ${dateFormat.format(Date(customer.dateOfJoiningEpochMs))}"

                bindingNonNull.textPreviousDues.text = "₹${data.previousDues}"
                
                bindingNonNull.textTotalDue.text = "₹${data.totalDue}"

                if (data.currentPayment != null) {
                    bindingNonNull.textCustomerPricing.visibility = View.VISIBLE
                    bindingNonNull.textCustomerPricing.text =
                        "Base Fee: ₹${data.currentPayment.baseMonthlyFee} | Per Tiffin: ₹${data.currentPayment.tiffinPrice}"
                } else {
                    bindingNonNull.textCustomerPricing.visibility = View.GONE
                }

                if (customer.photoUri != null) {
                    try {
                        bindingNonNull.imageCustomerPhoto.setImageURI(Uri.parse(customer.photoUri))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    bindingNonNull.imageCustomerPhoto.setImageResource(android.R.drawable.ic_menu_camera)
                }
            }
        }

        // ── Toggle billing card vs "no cycle" card ─────────────────────────
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentMonthPayment.collect { payment ->
                if (payment != null) {
                    bindingNonNull.cardBilling.visibility = View.VISIBLE
                    bindingNonNull.cardNoCycle.visibility = View.GONE
                } else {
                    bindingNonNull.cardBilling.visibility = View.GONE
                    bindingNonNull.cardNoCycle.visibility = View.VISIBLE
                }
            }
        }

        // ── Absence log RecyclerView ─────────────────────────────────────────
        val absenceAdapter = AbsenceAdapter(
            onDelete = { entity ->
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Remove Absence?")
                    .setMessage("Remove ${if (entity.slot == "LUNCH") "Lunch" else "Dinner"} absence? The monthly due will be recalculated.")
                    .setPositiveButton("Remove") { _, _ -> viewModel.removeAbsence(entity) }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        )
        bindingNonNull.recyclerAbsences.layoutManager = LinearLayoutManager(requireContext())
        bindingNonNull.recyclerAbsences.adapter = absenceAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentMonthAbsences.collect { absences ->
                absenceAdapter.submitList(absences)
                bindingNonNull.textNoAbsences.visibility =
                    if (absences.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        // ── Add Absence button ───────────────────────────────────────────────
        bindingNonNull.buttonAddAbsence.setOnClickListener {
            showAddAbsenceFlow(viewModel)
        }

        // ── Share due reminder ───────────────────────────────────────────────
        bindingNonNull.buttonShareDue.setOnClickListener {
            val customer = viewModel.customer.value
            val nextDue = viewModel.nextDuePayment.value
            val totalDue = viewModel.totalDue.value
            val currentPayment = viewModel.currentMonthPayment.value

            if (customer == null) return@setOnClickListener

            if (nextDue != null) {
                val message = ShareUtils.buildDueMessage(
                    customerName = customer.name,
                    month = nextDue.month,
                    year = nextDue.year,
                    dueAmount = nextDue.dueAmount
                )
                ShareUtils.shareText(requireContext(), message)
            } else {
                val now = Calendar.getInstance()
                val currentMonth = now.get(Calendar.MONTH) + 1
                val currentYear = now.get(Calendar.YEAR)

                val dueToShare = if (totalDue > 0) totalDue else {
                    if (currentPayment == null) 0L else currentPayment.baseMonthlyFee
                }

                if (dueToShare <= 0L) {
                    Toast.makeText(requireContext(), "No due payments.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val message = ShareUtils.buildDueMessage(
                    customerName = customer.name,
                    month = currentMonth,
                    year = currentYear,
                    dueAmount = dueToShare
                )
                ShareUtils.shareText(requireContext(), message)
            }
        }

        bindingNonNull.buttonOpenPayments.setOnClickListener {
            findNavController().navigate(
                R.id.action_customerDetails_to_paymentHistory,
                bundleOf("customerId" to customerId)
            )
        }

        bindingNonNull.buttonEditCustomer.setOnClickListener {
            val current = viewModel.customer.value ?: return@setOnClickListener
            val dialog = CustomerDialogFragment.newInstance("Edit Customer")
            dialog.initialData = CustomerDialogResult(
                name = current.name,
                phone = current.phone,
                address = current.address,
                dateOfJoiningEpochMs = current.dateOfJoiningEpochMs,
                photoUri = current.photoUri,
                baseMonthlyFee = current.baseMonthlyFee, // Ignored in UI, but kept in data
                tiffinPrice = current.tiffinPrice
            )
            dialog.onSaveCallback = { result ->
                viewModel.updateCustomer(
                    name = result.name,
                    phone = result.phone,
                    address = result.address,
                    dateOfJoiningEpochMs = result.dateOfJoiningEpochMs,
                    photoUri = result.photoUri,
                    baseMonthlyFee = result.baseMonthlyFee,
                    tiffinPrice = result.tiffinPrice
                )
            }
            dialog.show(childFragmentManager, "CustomerDialog")
        }

        bindingNonNull.buttonDeleteCustomer.setOnClickListener {
            viewModel.deleteCustomer()
            findNavController().popBackStack()
        }
    }

    /**
     * Step 1 — pick a date within current billing cycle.
     * Step 2 — choose Lunch, Dinner, or Both.
     */
    private fun showAddAbsenceFlow(viewModel: CustomerDetailsViewModel) {
        val cycle = viewModel.currentBillingCycle.value
        if (cycle == null) {
            Toast.makeText(requireContext(), "Wait for customer data to load", Toast.LENGTH_SHORT).show()
            return
        }

        val now = Calendar.getInstance()
        val currentYear = now.get(Calendar.YEAR)
        val currentMonth = now.get(Calendar.MONTH)

        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val cal = Calendar.getInstance()
                cal.set(year, month, day, 12, 0, 0)
                cal.set(Calendar.MILLISECOND, 0)
                val epochDay = cal.timeInMillis / 86_400_000L

                showSlotPickerDialog(viewModel, epochDay)
            },
            currentYear,
            currentMonth,
            now.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = cycle.startDateMs
            datePicker.maxDate = cycle.endDateMs
        }.show()
    }

    /** Let the user choose Lunch, Dinner, or Both — then record the absence(s). */
    private fun showSlotPickerDialog(viewModel: CustomerDetailsViewModel, epochDay: Long) {
        val slots = arrayOf("☀️  Lunch", "🌙  Dinner", "☀️🌙  Both (Lunch & Dinner)")

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Which meal was skipped?")
            .setItems(slots) { _, which ->
                when (which) {
                    0 -> viewModel.addAbsence(epochDay, "LUNCH")
                    1 -> viewModel.addAbsence(epochDay, "DINNER")
                    2 -> {
                        viewModel.addAbsence(epochDay, "LUNCH")
                        viewModel.addAbsence(epochDay, "DINNER")
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
