package com.example.tiffin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiffin.TiffinApp
import com.example.tiffin.databinding.FragmentPaymentHistoryBinding
import com.example.tiffin.ui.adapters.PaymentAdapter
import com.example.tiffin.ui.dialogs.PaymentDialog
import com.example.tiffin.ui.dialogs.PaymentDialogResult
import com.example.tiffin.ui.viewmodel.PaymentHistoryViewModel
import com.example.tiffin.ui.viewmodel.PaymentHistoryViewModelFactory
import kotlinx.coroutines.launch
import com.example.tiffin.domain.DueCalculator

class PaymentHistoryFragment : Fragment() {

    private var binding: FragmentPaymentHistoryBinding? = null

    private val bindingNonNull: FragmentPaymentHistoryBinding
        get() = requireNotNull(binding)

    private val customerId: Long by lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().getLong("customerId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentHistoryBinding.inflate(inflater, container, false)
        return bindingNonNull.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingNonNull.textPaymentsTitle.text = "Payments for Customer #$customerId"

        val app = requireActivity().application as TiffinApp
        val viewModel = androidx.lifecycle.ViewModelProvider(
            this,
            PaymentHistoryViewModelFactory(customerId = customerId, paymentRepository = app.paymentRepository)
        )[PaymentHistoryViewModel::class.java]

        var customerName: String = "Customer"
        var customerJoinDateMs: Long = System.currentTimeMillis()
        
        val adapter = PaymentAdapter(
            onShare = { payment ->
                val due = DueCalculator.calculateDue(payment.amountToPay, payment.amountPaid)
                if (due == 0L) {
                    Toast.makeText(requireContext(), "No due for this month.", Toast.LENGTH_SHORT).show()
                    return@PaymentAdapter
                }
                val message = ShareUtils.buildDueMessage(
                    customerName = customerName,
                    month = payment.month,
                    year = payment.year,
                    dueAmount = due
                )
                ShareUtils.shareText(requireContext(), message)
            },
            onEdit = { payment ->
                PaymentDialog.show(
                    fragment = this,
                    title = "Edit Payment",
                    initial = PaymentDialogResult(
                        month = payment.month,
                        year = payment.year,
                        amountToPay = payment.amountToPay,
                        amountPaid = payment.amountPaid,
                        absentDays = payment.absentDays,
                        startDateMs = payment.startDateMs,
                        endDateMs = payment.endDateMs,
                        baseMonthlyFee = payment.baseMonthlyFee,
                        tiffinPrice = payment.tiffinPrice
                    ),
                    onSave = { result ->
                        viewModel.upsertPayment(
                            year = result.year,
                            month = result.month,
                            amountToPay = result.amountToPay,
                            amountPaid = result.amountPaid,
                            absentDays = result.absentDays,
                            startDateMs = result.startDateMs,
                            endDateMs = result.endDateMs,
                            baseMonthlyFee = result.baseMonthlyFee,
                            tiffinPrice = result.tiffinPrice
                        )
                    }
                )
            },
            onDelete = { payment -> viewModel.deletePayment(payment) }
        )

        bindingNonNull.recyclerPayments.layoutManager = LinearLayoutManager(requireContext())
        bindingNonNull.recyclerPayments.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            app.customerRepository.observeCustomer(customerId).collect { customer ->
                if (customer != null) {
                    customerName = customer.name
                    customerJoinDateMs = customer.dateOfJoiningEpochMs
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.payments.collect { adapter.submitList(it) }
        }

        bindingNonNull.fabAddPayment.setOnClickListener {
            PaymentDialog.show(
                fragment = this,
                title = "Add Payment",
                initial = null
            ) { result ->
                viewModel.upsertPayment(
                    year = result.year,
                    month = result.month,
                    amountToPay = result.amountToPay,
                    amountPaid = result.amountPaid,
                    absentDays = result.absentDays,
                    startDateMs = result.startDateMs,
                    endDateMs = result.endDateMs,
                    baseMonthlyFee = result.baseMonthlyFee,
                    tiffinPrice = result.tiffinPrice
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

