package com.example.tiffin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TiffinAbsenceDao {

    /** Observe all absences for a customer in a specific month, ordered by date then slot. */
    @Query(
        """
        SELECT * FROM tiffin_absences
        WHERE customerId = :customerId AND year = :year AND month = :month
        ORDER BY dateEpochDay ASC, slot ASC
        """
    )
    fun observeAbsences(customerId: Long, year: Int, month: Int): Flow<List<TiffinAbsenceEntity>>

    /**
     * Count total missed tiffins (each slot counts as 1).
     * Used for billing: each absent meal deducts 1 × tiffinPrice from the base fee.
     */
    @Query(
        """
        SELECT COUNT(*)
        FROM tiffin_absences
        WHERE customerId = :customerId AND year = :year AND month = :month
        """
    )
    suspend fun countTotalAbsentMeals(customerId: Long, year: Int, month: Int): Int

    /** Silently ignores duplicates (same customer + date + slot). */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: TiffinAbsenceEntity): Long

    @Delete
    suspend fun delete(entity: TiffinAbsenceEntity)

    @Query("DELETE FROM tiffin_absences WHERE customerId = :customerId")
    suspend fun deleteByCustomer(customerId: Long)

    @Query("SELECT * FROM tiffin_absences")
    suspend fun getAllAbsences(): List<TiffinAbsenceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAbsences(absences: List<TiffinAbsenceEntity>)

    @Query("DELETE FROM tiffin_absences")
    suspend fun deleteAllAbsences()
}
