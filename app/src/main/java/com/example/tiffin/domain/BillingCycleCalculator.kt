package com.example.tiffin.domain

import java.util.Calendar

data class BillingCycle(
    val year: Int,
    val month: Int, // 1-12 based, representing the start month of this billing cycle
    val startDateMs: Long,
    val endDateMs: Long
)

object BillingCycleCalculator {
    /**
     * Given a customer's joined timestamp and a target timestamp (usually 'now' or an absence date),
     * computes the distinct billing cycle that the target date falls into.
     * The cycle is bounded by the exact Join Day of every month.
     */
    fun getCycleForDate(joinDateMs: Long, targetDateMs: Long): BillingCycle {
        val joinCal = Calendar.getInstance().apply {
            timeInMillis = joinDateMs
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val targetCal = Calendar.getInstance().apply { timeInMillis = targetDateMs }
        
        val yearsDiff = targetCal.get(Calendar.YEAR) - joinCal.get(Calendar.YEAR)
        val monthsDiff = targetCal.get(Calendar.MONTH) - joinCal.get(Calendar.MONTH)
        val approxOffset = yearsDiff * 12 + monthsDiff
        
        // Check offsets around approxOffset to accurately find the bounding cycle
        for (offset in (approxOffset - 2)..(approxOffset + 2)) {
            val startCal = joinCal.clone() as Calendar
            startCal.add(Calendar.MONTH, offset)
            
            val endCal = joinCal.clone() as Calendar
            endCal.add(Calendar.MONTH, offset + 1)
            endCal.add(Calendar.DAY_OF_MONTH, -1)
            endCal.set(Calendar.HOUR_OF_DAY, 23)
            endCal.set(Calendar.MINUTE, 59)
            endCal.set(Calendar.SECOND, 59)
            endCal.set(Calendar.MILLISECOND, 999)
            
            if (targetDateMs >= startCal.timeInMillis && targetDateMs <= endCal.timeInMillis) {
                return BillingCycle(
                    year = startCal.get(Calendar.YEAR),
                    month = startCal.get(Calendar.MONTH) + 1,
                    startDateMs = startCal.timeInMillis,
                    endDateMs = endCal.timeInMillis
                )
            }
        }
        
        // Fallback (should not be reached unless targetDateMs is extremely out of bounds)
        val startCal = joinCal.clone() as Calendar
        startCal.add(Calendar.MONTH, approxOffset)
            
        val endCal = joinCal.clone() as Calendar
        endCal.add(Calendar.MONTH, approxOffset + 1)
        endCal.add(Calendar.DAY_OF_MONTH, -1)
        endCal.set(Calendar.HOUR_OF_DAY, 23)
        endCal.set(Calendar.MINUTE, 59)
        endCal.set(Calendar.SECOND, 59)
        endCal.set(Calendar.MILLISECOND, 999)

        return BillingCycle(
            year = startCal.get(Calendar.YEAR),
            month = startCal.get(Calendar.MONTH) + 1,
            startDateMs = startCal.timeInMillis,
            endDateMs = endCal.timeInMillis
        )
    }

    /** Helper for obtaining the cycle of 'today' based on the join date. */
    fun getCurrentCycle(joinDateMs: Long): BillingCycle {
        return getCycleForDate(joinDateMs, System.currentTimeMillis())
    }

    /**
     * Finds the specific BillingCycle bounds that match the given start year and month.
     * Guaranteed to return exact month cycle boundaries matching the join date.
     */
    fun getCycleByYearMonth(joinDateMs: Long, year: Int, month: Int): BillingCycle? {
        val joinCal = Calendar.getInstance().apply {
            timeInMillis = joinDateMs
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val targetMonthIndex = month - 1
        val yearsDiff = year - joinCal.get(Calendar.YEAR)
        val monthsDiff = targetMonthIndex - joinCal.get(Calendar.MONTH)
        val approxOffset = yearsDiff * 12 + monthsDiff

        for (offset in (approxOffset - 2)..(approxOffset + 2)) {
            val startCal = joinCal.clone() as Calendar
            startCal.add(Calendar.MONTH, offset)
            
            if (startCal.get(Calendar.YEAR) == year && startCal.get(Calendar.MONTH) == targetMonthIndex) {
                val endCal = joinCal.clone() as Calendar
                endCal.add(Calendar.MONTH, offset + 1)
                endCal.add(Calendar.DAY_OF_MONTH, -1)
                endCal.set(Calendar.HOUR_OF_DAY, 23)
                endCal.set(Calendar.MINUTE, 59)
                endCal.set(Calendar.SECOND, 59)
                endCal.set(Calendar.MILLISECOND, 999)
                
                return BillingCycle(
                    year = startCal.get(Calendar.YEAR),
                    month = startCal.get(Calendar.MONTH) + 1,
                    startDateMs = startCal.timeInMillis,
                    endDateMs = endCal.timeInMillis
                )
            }
        }
        
        return null
    }
}
