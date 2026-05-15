package com.example.tiffin.repository

import com.example.tiffin.data.PaymentDao
import com.example.tiffin.data.PaymentEarliestDue
import com.example.tiffin.data.PaymentEntity
import kotlinx.coroutines.flow.Flow

class PaymentRepository(
    private val paymentDao: PaymentDao
) {

    fun observePaymentsForCustomer(customerId: Long): Flow<List<PaymentEntity>> =
        paymentDao.observePaymentsForCustomer(customerId)

    fun observeTotalDue(customerId: Long): Flow<Long> = paymentDao.observeTotalDue(customerId)

    fun observeNextDuePayment(customerId: Long): Flow<PaymentEarliestDue?> =
        paymentDao.observeNextDuePayment(customerId)

    suspend fun upsertPayment(
        customerId: Long,
        year: Int,
        month: Int,
        amountToPay: Long,
        amountPaid: Long,
        absentDays: Int = 0,
        startDateMs: Long,
        endDateMs: Long,
        baseMonthlyFee: Long,
        tiffinPrice: Long
    ) {
        val existing = paymentDao.getPaymentByUnique(customerId = customerId, year = year, month = month)
        if (existing == null) {
            paymentDao.insertPayment(
                PaymentEntity(
                    id = 0L,
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
            )
        } else {
            paymentDao.updatePayment(
                existing.copy(
                    amountToPay = amountToPay,
                    amountPaid = amountPaid,
                    absentDays = absentDays,
                    startDateMs = startDateMs,
                    endDateMs = endDateMs,
                    baseMonthlyFee = baseMonthlyFee,
                    tiffinPrice = tiffinPrice
                )
            )
        }
    }

    suspend fun deletePayment(payment: PaymentEntity) {
        paymentDao.deletePayment(payment)
    }
}

