package com.example.tiffin

import android.app.Application
import com.example.tiffin.data.AppDatabase
import com.example.tiffin.repository.CustomerRepository
import com.example.tiffin.repository.PaymentRepository
import com.example.tiffin.repository.TiffinAbsenceRepository

import com.google.android.material.color.DynamicColors

class TiffinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    val customerRepository: CustomerRepository by lazy {
        CustomerRepository(
            customerDao = database.customerDao(),
            paymentDao = database.paymentDao(),
            absenceDao = database.tiffinAbsenceDao()
        )
    }

    val paymentRepository: PaymentRepository by lazy { PaymentRepository(database.paymentDao()) }

    val absenceRepository: TiffinAbsenceRepository by lazy {
        TiffinAbsenceRepository(
            absenceDao = database.tiffinAbsenceDao(),
            paymentDao = database.paymentDao(),
            customerDao = database.customerDao()
        )
    }
}

