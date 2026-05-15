package com.example.tiffin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val phone: String,
    val address: String?,
    val dateOfJoiningEpochMs: Long,
    val photoUri: String? = null,
    val baseMonthlyFee: Long = 0L,
    val tiffinPrice: Long = 0L
)

