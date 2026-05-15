package com.example.tiffin.repository

import com.example.tiffin.data.CustomerDao
import com.example.tiffin.data.CustomerEntity
import com.example.tiffin.data.PaymentDao
import com.example.tiffin.data.CustomerListItem
import com.example.tiffin.data.TiffinAbsenceDao
import kotlinx.coroutines.flow.Flow

class CustomerRepository(
    private val customerDao: CustomerDao,
    private val paymentDao: PaymentDao,
    private val absenceDao: TiffinAbsenceDao
) {

    val customersWithDue: Flow<List<CustomerListItem>> = customerDao.observeCustomersWithDue()

    fun observeCustomer(customerId: Long): Flow<CustomerEntity?> = customerDao.observeCustomer(customerId)

    suspend fun insertCustomer(customer: CustomerEntity): Long = customerDao.insertCustomer(customer)

    suspend fun updateCustomer(customer: CustomerEntity) = customerDao.updateCustomer(customer)

    suspend fun deleteCustomer(customerId: Long) {
        // Since we didn't enforce a foreign key cascade, delete child records manually.
        absenceDao.deleteByCustomer(customerId)
        paymentDao.deletePaymentsByCustomer(customerId)
        customerDao.deleteCustomerById(customerId)
    }
}

