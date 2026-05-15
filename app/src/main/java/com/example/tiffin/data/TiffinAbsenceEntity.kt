package com.example.tiffin.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Represents a single tiffin absence slot for a customer on a specific date.
 * [slot] is either "LUNCH" or "DINNER".
 * Two rows with the same (customerId, dateEpochDay) but different slots means
 * the customer missed both meals that day.
 */
@Entity(
    tableName = "tiffin_absences",
    indices = [
        Index(value = ["customerId", "dateEpochDay", "slot"], unique = true)
    ]
)
data class TiffinAbsenceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val customerId: Long,
    val month: Int,   // 1–12
    val year: Int,
    /** Days since Unix epoch (UTC). Use Calendar noon to avoid DST edge cases. */
    val dateEpochDay: Long,
    /** "LUNCH" or "DINNER" */
    val slot: String
)
