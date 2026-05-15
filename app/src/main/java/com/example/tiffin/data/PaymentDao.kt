package com.example.tiffin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

data class PaymentEarliestDue(
    val id: Long,
    val month: Int,
    val year: Int,
    val amountToPay: Long,
    val amountPaid: Long,
    val dueAmount: Long
)

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payments WHERE customerId = :customerId ORDER BY year ASC, month ASC")
    fun observePaymentsForCustomer(customerId: Long): Flow<List<PaymentEntity>>

    @Query(
        """
        SELECT COALESCE(
            SUM(
                CASE 
                    WHEN amountPaid >= amountToPay THEN 0
                    ELSE amountToPay - amountPaid
                END
            ),
        0)
        FROM payments
        WHERE customerId = :customerId
        """
    )
    fun observeTotalDue(customerId: Long): Flow<Long>

    @Query(
        """
        SELECT 
            id,
            month,
            year,
            amountToPay,
            amountPaid,
            (amountToPay - amountPaid) AS dueAmount
        FROM payments
        WHERE customerId = :customerId
          AND amountPaid < amountToPay
        ORDER BY year ASC, month ASC
        LIMIT 1
        """
    )
    fun observeNextDuePayment(customerId: Long): Flow<PaymentEarliestDue?>

    @Query(
        """
        SELECT * FROM payments
        WHERE customerId = :customerId
          AND year = :year
          AND month = :month
        LIMIT 1
        """
    )
    suspend fun getPaymentByUnique(
        customerId: Long,
        year: Int,
        month: Int
    ): PaymentEntity?

    @Query("DELETE FROM payments WHERE customerId = :customerId")
    suspend fun deletePaymentsByCustomer(customerId: Long)

    @Insert
    suspend fun insertPayment(payment: PaymentEntity): Long

    @Update
    suspend fun updatePayment(payment: PaymentEntity)

    @Delete
    suspend fun deletePayment(payment: PaymentEntity)

    @Query("SELECT * FROM payments")
    suspend fun getAllPayments(): List<PaymentEntity>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAllPayments(payments: List<PaymentEntity>)

    @Query("DELETE FROM payments")
    suspend fun deleteAllPayments()
}
