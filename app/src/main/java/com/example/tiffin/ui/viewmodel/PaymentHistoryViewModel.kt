package com.example.tiffin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tiffin.data.PaymentEntity
import com.example.tiffin.repository.PaymentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PaymentHistoryViewModel(
    private val customerId: Long,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    val payments: StateFlow<List<PaymentEntity>> =
        paymentRepository.observePaymentsForCustomer(customerId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun upsertPayment(
        year: Int,
        month: Int,
        amountToPay: Long,
        amountPaid: Long,
        absentDays: Int,
        startDateMs: Long,
        endDateMs: Long,
        baseMonthlyFee: Long,
        tiffinPrice: Long
    ) {
        viewModelScope.launch {
            paymentRepository.upsertPayment(
                customerId = customerId,
                year = year,
                month = month,
                amountToPay = amountToPay,
                amountPaid = amountPaid,
                absentDays = absentDays,
                startDateMs = startDateMs,
                endDateMs = endDateMs,
                baseMonthlyFee = baseMonthlyFee,
                tiffinPrice = tiffinPrice
            )
        }
    }

    fun deletePayment(payment: PaymentEntity) {
        viewModelScope.launch {
            paymentRepository.deletePayment(payment)
        }
    }
}

class PaymentHistoryViewModelFactory(
    private val customerId: Long,
    private val paymentRepository: PaymentRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentHistoryViewModel(
            customerId = customerId,
            paymentRepository = paymentRepository
        ) as T
    }
}

