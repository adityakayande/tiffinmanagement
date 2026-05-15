package com.example.tiffin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tiffin.data.CustomerEntity
import com.example.tiffin.repository.CustomerRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CustomerListViewModel(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    val customers: StateFlow<List<com.example.tiffin.data.CustomerListItem>> =
        customerRepository.customersWithDue.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addCustomer(
        name: String,
        phone: String,
        address: String?,
        dateOfJoiningEpochMs: Long,
        photoUri: String?,
        baseMonthlyFee: Long,
        tiffinPrice: Long
    ) {
        viewModelScope.launch {
            customerRepository.insertCustomer(
                CustomerEntity(
                    id = 0L,
                    name = name,
                    phone = phone,
                    address = address,
                    dateOfJoiningEpochMs = dateOfJoiningEpochMs,
                    photoUri = photoUri,
                    baseMonthlyFee = baseMonthlyFee,
                    tiffinPrice = tiffinPrice
                )
            )
        }
    }
}

class CustomerListViewModelFactory(
    private val customerRepository: CustomerRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CustomerListViewModel(customerRepository) as T
    }
}

