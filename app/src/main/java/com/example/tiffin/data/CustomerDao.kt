package com.example.tiffin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

data class CustomerListItem(
    val id: Long,
    val name: String,
    val phone: String,
    val totalDue: Long,
    val photoUri: String? = null
)

@Dao
interface CustomerDao {

    @Query(
        """
        SELECT 
            c.id AS id,
            c.name AS name,
            c.phone AS phone,
            c.photoUri AS photoUri,
            COALESCE(SUM(
                CASE 
                    WHEN p.amountPaid >= p.amountToPay THEN 0
                    ELSE p.amountToPay - p.amountPaid
                END
            ), 0) AS totalDue
        FROM customers c
        LEFT JOIN payments p ON p.customerId = c.id
        GROUP BY c.id, c.name, c.phone, c.photoUri
        ORDER BY c.name ASC
        """
    )
    fun observeCustomersWithDue(): Flow<List<CustomerListItem>>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun observeCustomer(customerId: Long): Flow<CustomerEntity?>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    suspend fun getCustomerOnce(customerId: Long): CustomerEntity?

    @Insert
    suspend fun insertCustomer(customer: CustomerEntity): Long

    @Update
    suspend fun updateCustomer(customer: CustomerEntity)

    @Query("DELETE FROM customers WHERE id = :customerId")
    suspend fun deleteCustomerById(customerId: Long)

    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers(): List<CustomerEntity>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAllCustomers(customers: List<CustomerEntity>)

    @Query("DELETE FROM customers")
    suspend fun deleteAllCustomers()
}
