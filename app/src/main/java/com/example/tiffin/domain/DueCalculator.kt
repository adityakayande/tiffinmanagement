package com.example.tiffin.domain

import kotlin.math.max

data class PaymentDue(
    val dueAmount: Long,
    val status: PaymentStatus
)

object DueCalculator {

    fun calculateDue(amountToPay: Long, amountPaid: Long): Long {
        // Pending amount can never be negative.
        return max(0L, amountToPay - amountPaid)
    }

    fun calculateStatus(amountToPay: Long, amountPaid: Long): PaymentStatus {
        val due = calculateDue(amountToPay, amountPaid)
        return when {
            due == 0L -> PaymentStatus.Paid
            amountPaid == 0L -> PaymentStatus.Due
            else -> PaymentStatus.Partial
        }
    }

    fun calculate(amountToPay: Long, amountPaid: Long): PaymentDue {
        val dueAmount = calculateDue(amountToPay, amountPaid)
        val status = calculateStatus(amountToPay, amountPaid)
        return PaymentDue(dueAmount = dueAmount, status = status)
    }

    fun calculateQuickCustomerStatus(totalDue: Long): PaymentStatus {
        return if (totalDue == 0L) PaymentStatus.Paid else PaymentStatus.Due
    }
}

