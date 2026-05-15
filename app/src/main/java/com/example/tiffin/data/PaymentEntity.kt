package com.example.tiffin.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "payments",
    indices = [
        Index(value = ["customerId", "year", "month"], unique = true)
    ]
)
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val customerId: Long,
    val month: Int,
    val year: Int,
    val amountToPay: Long,
    val amountPaid: Long,
    val absentDays: Int = 0,
    val startDateMs: Long = 0L,
    val endDateMs: Long = 0L,
    val baseMonthlyFee: Long = 0L,
    val tiffinPrice: Long = 0L
)
