package com.example.tiffin.ui.dialogs

import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.tiffin.databinding.DialogPaymentBinding
import com.example.tiffin.domain.BillingCycleCalculator
import com.google.android.material.dialog.MaterialAlertDialogBuilder

data class PaymentDialogResult(
    val month: Int,
    val year: Int,
    val amountToPay: Long,
    val amountPaid: Long,
    val absentDays: Int,
    val startDateMs: Long,
    val endDateMs: Long,
    val baseMonthlyFee: Long,
    val tiffinPrice: Long
)

object PaymentDialog {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    fun show(
        fragment: Fragment,
        title: String,
        initial: PaymentDialogResult?,
        onSave: (PaymentDialogResult) -> Unit
    ) {
        val binding = DialogPaymentBinding.inflate(fragment.layoutInflater)

        var selectedMonth: Int? = null
        var selectedYear: Int? = null
        var selectedStartMs: Long? = null
        var selectedEndMs: Long? = null

        fun updateCycleText(startMs: Long, endMs: Long) {
            val startStr = dateFormat.format(startMs)
            val endStr = dateFormat.format(endMs)
            binding.textCycleBounds.text = "Cycle: $startStr to $endStr"
        }

        if (initial != null) {
            selectedMonth = initial.month
            selectedYear = initial.year
            selectedStartMs = initial.startDateMs
            selectedEndMs = initial.endDateMs
            updateCycleText(initial.startDateMs, initial.endDateMs)

            binding.inputAmountToPay.setText(initial.amountToPay.toString())
            binding.inputAmountPaid.setText(initial.amountPaid.toString())
            binding.inputAbsentDays.setText(initial.absentDays.toString())
            binding.inputBaseFee.setText(initial.baseMonthlyFee.toString())
            binding.inputTiffinPrice.setText(initial.tiffinPrice.toString())
        } else {
            binding.inputBaseFee.setText("2400")
            binding.inputTiffinPrice.setText("40")
        }

        binding.btnSelectCycle.setOnClickListener {
            val now = Calendar.getInstance()
            DatePickerDialog(
                fragment.requireContext(),
                { _, year, month, day ->
                    val startCal = Calendar.getInstance().apply {
                        set(year, month, day, 0, 0, 0)
                        set(Calendar.MILLISECOND, 0)
                    }
                    val endCal = startCal.clone() as Calendar
                    endCal.add(Calendar.MONTH, 1)

                    selectedMonth = month + 1
                    selectedYear = year
                    selectedStartMs = startCal.timeInMillis
                    selectedEndMs = endCal.timeInMillis
                    
                    updateCycleText(startCal.timeInMillis, endCal.timeInMillis)
                    
                    // Trigger validation
                    binding.inputAmountToPay.text = binding.inputAmountToPay.text
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val dialog = MaterialAlertDialogBuilder(fragment.requireContext())
            .setTitle(title)
            .setView(binding.root)
            .setPositiveButton("Save", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialog.setOnShowListener {
            val saveButton = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
            
            fun parseLong(text: String): Long? = text.toLongOrNull()
            fun parseInt(text: String): Int? = text.toIntOrNull()

            fun validate(): Boolean {
                val toPay = parseLong(binding.inputAmountToPay.text?.toString()?.trim().orEmpty())
                val paid  = parseLong(binding.inputAmountPaid.text?.toString()?.trim().orEmpty())
                val absentDays = parseInt(binding.inputAbsentDays.text?.toString()?.trim().orEmpty()) ?: 0
                val baseFee = parseLong(binding.inputBaseFee.text?.toString()?.trim().orEmpty())
                val tiffinPrice = parseLong(binding.inputTiffinPrice.text?.toString()?.trim().orEmpty())

                val ok = selectedMonth != null && selectedYear != null &&
                    selectedStartMs != null && selectedEndMs != null &&
                    toPay != null && toPay >= 0 &&
                    paid  != null && paid  >= 0 &&
                    baseFee != null && baseFee >= 0 &&
                    tiffinPrice != null && tiffinPrice >= 0 &&
                    absentDays >= 0

                saveButton.isEnabled = ok
                return ok
            }

            val watcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { validate() }
                override fun afterTextChanged(s: Editable?) {}
            }

            binding.inputAmountToPay.addTextChangedListener(watcher)
            binding.inputAmountPaid.addTextChangedListener(watcher)
            binding.inputAbsentDays.addTextChangedListener(watcher)
            binding.inputBaseFee.addTextChangedListener(watcher)
            binding.inputTiffinPrice.addTextChangedListener(watcher)

            validate()

            saveButton.setOnClickListener {
                if (!validate()) return@setOnClickListener
                val toPay     = binding.inputAmountToPay.text!!.toString().trim().toLong()
                val paid      = binding.inputAmountPaid.text!!.toString().trim().toLong()
                val absentDays = binding.inputAbsentDays.text?.toString()?.trim()?.toIntOrNull() ?: 0
                val baseFee   = binding.inputBaseFee.text!!.toString().trim().toLong()
                val tiffinPrice = binding.inputTiffinPrice.text!!.toString().trim().toLong()
                onSave(
                    PaymentDialogResult(
                        month        = selectedMonth!!,
                        year         = selectedYear!!,
                        amountToPay  = toPay,
                        amountPaid   = paid,
                        absentDays   = absentDays,
                        startDateMs  = selectedStartMs!!,
                        endDateMs    = selectedEndMs!!,
                        baseMonthlyFee = baseFee,
                        tiffinPrice  = tiffinPrice
                    )
                )
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}
