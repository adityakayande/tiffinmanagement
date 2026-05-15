package com.example.tiffin.repository

import com.example.tiffin.data.CustomerDao
import com.example.tiffin.data.PaymentDao
import com.example.tiffin.data.PaymentEntity
import com.example.tiffin.data.TiffinAbsenceDao
import com.example.tiffin.data.TiffinAbsenceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.withLock

class TiffinAbsenceRepository(
    private val absenceDao: TiffinAbsenceDao,
    private val paymentDao: PaymentDao,
    private val customerDao: CustomerDao
) {

    fun observeAbsences(customerId: Long, year: Int, month: Int): Flow<List<TiffinAbsenceEntity>> =
        absenceDao.observeAbsences(customerId, year, month)

    /**
     * Record a tiffin absence for the given date and slot ("LUNCH" or "DINNER").
     * Automatically recalculates and upserts the payment record for that month.
     */
    suspend fun addAbsence(
        customerId: Long,
        year: Int,
        month: Int,
        dateEpochDay: Long,
        slot: String
    ) {
        absenceDao.insert(
            TiffinAbsenceEntity(
                customerId = customerId,
                year = year,
                month = month,
                dateEpochDay = dateEpochDay,
                slot = slot
            )
        )
        recalculateMonthlyBill(customerId, year, month)
    }

    /**
     * Remove a specific absence record and recalculate the monthly bill.
     */
    suspend fun removeAbsence(entity: TiffinAbsenceEntity) {
        absenceDao.delete(entity)
        recalculateMonthlyBill(entity.customerId, entity.year, entity.month)
    }

    private val mutex = kotlinx.coroutines.sync.Mutex()

    /**
     * Recomputes amountToPay for the given month based on the number of missed meals.
     * Billing rule: each absent meal (Lunch or Dinner) deducts 1 × tiffinPrice.
     */
    private suspend fun recalculateMonthlyBill(customerId: Long, year: Int, month: Int) {
        mutex.withLock {
            val absentMeals = absenceDao.countTotalAbsentMeals(customerId, year, month)
            val existing = paymentDao.getPaymentByUnique(customerId, year, month)
            
            if (existing != null) {
                // Determine tiffinPrice: prefer the cycle's price, fallback to customer's.
                val cust = customerDao.getCustomerOnce(customerId)
                val tiffinPrice = if (existing.tiffinPrice > 0) existing.tiffinPrice else (cust?.tiffinPrice ?: 0L)
                
                // Determine baseFee: prefer the cycle's fee, fallback to customer's, or as a last 
                // resort, deduce from current amountToPay to prevent wiping manually entered values.
                val baseFee = if (existing.baseMonthlyFee > 0) {
                    existing.baseMonthlyFee
                } else if (cust != null && cust.baseMonthlyFee > 0) {
                    cust.baseMonthlyFee
                } else {
                    existing.amountToPay + (existing.absentDays * tiffinPrice)
                }

                val newAmountToPay = maxOf(0L, baseFee - (absentMeals * tiffinPrice))
                paymentDao.updatePayment(
                    existing.copy(
                        amountToPay = newAmountToPay,
                        absentDays = absentMeals
                    )
                )
            }
        }
    }
}
