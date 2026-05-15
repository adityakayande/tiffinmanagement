package com.example.tiffin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tiffin.data.CustomerEntity
import com.example.tiffin.data.PaymentEarliestDue
import com.example.tiffin.data.TiffinAbsenceEntity
import com.example.tiffin.repository.CustomerRepository
import com.example.tiffin.repository.PaymentRepository
import com.example.tiffin.repository.TiffinAbsenceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

import kotlinx.coroutines.flow.flatMapLatest

class CustomerDetailsViewModel(
    private val customerId: Long,
    private val customerRepository: CustomerRepository,
    private val paymentRepository: PaymentRepository,
    private val absenceRepository: TiffinAbsenceRepository
) : ViewModel() {

    val customer: kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.CustomerEntity?> =
        customerRepository.observeCustomer(customerId).stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val totalDue: kotlinx.coroutines.flow.StateFlow<Long> =
        paymentRepository.observeTotalDue(customerId).stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = 0L
        )

    val nextDuePayment: kotlinx.coroutines.flow.StateFlow<com.example.tiffin.data.PaymentEarliestDue?> =
        paymentRepository.observeNextDuePayment(customerId).stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val allPayments: StateFlow<List<com.example.tiffin.data.PaymentEntity>> =
        paymentRepository.observePaymentsForCustomer(customerId).stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val currentMonthPayment: StateFlow<com.example.tiffin.data.PaymentEntity?> =
        allPayments.map { payments ->
            val nowMs = System.currentTimeMillis()
            payments.find { nowMs in it.startDateMs..it.endDateMs }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val currentBillingCycle: StateFlow<com.example.tiffin.domain.BillingCycle?> = currentMonthPayment.map { payment ->
        if (payment == null) null else com.example.tiffin.domain.BillingCycle(
            year = payment.year,
            month = payment.month,
            startDateMs = payment.startDateMs,
            endDateMs = payment.endDateMs
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val previousDues: StateFlow<Long> = kotlinx.coroutines.flow.combine(
        totalDue,
        currentMonthPayment
    ) { total, current ->
        val currentDue = current?.let { it.amountToPay - it.amountPaid } ?: 0L
        maxOf(0L, total - currentDue)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0L
    )

    /** All per-slot absence records for the current billing cycle, ordered by date then slot. */
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    val currentMonthAbsences: StateFlow<List<TiffinAbsenceEntity>> =
        currentBillingCycle.flatMapLatest { cycle ->
            if (cycle == null) kotlinx.coroutines.flow.flowOf(emptyList())
            else absenceRepository.observeAbsences(customerId, cycle.year, cycle.month)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun updateCustomer(
        name: String,
        phone: String,
        address: String?,
        dateOfJoiningEpochMs: Long,
        photoUri: String?,
        baseMonthlyFee: Long,
        tiffinPrice: Long
    ) {
        viewModelScope.launch {
            val existing = customer.value ?: return@launch
            customerRepository.updateCustomer(
                existing.copy(
                    name = name,
                    phone = phone,
                    address = address,
                    dateOfJoiningEpochMs = dateOfJoiningEpochMs,
                    photoUri = photoUri,
                    baseMonthlyFee = baseMonthlyFee,
                    tiffinPrice = tiffinPrice
                )
            )
        }
    }

    /**
     * Add a tiffin absence. Finds the exact manual cycle the absence falls into.
     */
    fun addAbsence(dateEpochDay: Long, slot: String) {
        viewModelScope.launch {
            val targetDateMs = dateEpochDay * 86_400_000L
            val paymentForDate = allPayments.value.find { targetDateMs in it.startDateMs..it.endDateMs }
            
            if (paymentForDate == null) {
                // If it doesn't fall strictly inside an active manual payment cycle, ignore or we could default.
                // However, absences only subtract money if there's a payment cycle actively storing it.
                return@launch
            }
            
            absenceRepository.addAbsence(
                customerId = customerId,
                year = paymentForDate.year,
                month = paymentForDate.month,
                dateEpochDay = dateEpochDay,
                slot = slot
            )
        }
    }

    /** Remove a specific absence record; billing is auto-recalculated. */
    fun removeAbsence(entity: TiffinAbsenceEntity) {
        viewModelScope.launch {
            absenceRepository.removeAbsence(entity)
        }
    }

    fun deleteCustomer() {
        viewModelScope.launch {
            customerRepository.deleteCustomer(customerId)
        }
    }
}

class CustomerDetailsViewModelFactory(
    private val customerId: Long,
    private val customerRepository: CustomerRepository,
    private val paymentRepository: PaymentRepository,
    private val absenceRepository: TiffinAbsenceRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CustomerDetailsViewModel(
            customerId = customerId,
            customerRepository = customerRepository,
            paymentRepository = paymentRepository,
            absenceRepository = absenceRepository
        ) as T
    }
}
