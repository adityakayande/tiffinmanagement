package com.example.tiffin.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.text.DateFormatSymbols
import java.util.Locale

object ShareUtils {

    fun monthName(month1To12: Int, locale: Locale = Locale.getDefault()): String {
        val safe = month1To12.coerceIn(1, 12)
        // DateFormatSymbols months are 0-based.
        return DateFormatSymbols(locale).months[safe - 1]
    }

    fun buildDueMessage(
        customerName: String,
        month: Int,
        year: Int,
        dueAmount: Long
    ): String {
        val monthLabel = "${monthName(month)} $year"
        return "Hi $customerName, your tiffin payment due for $monthLabel is \u20B9$dueAmount. Please pay soon."
    }

    fun shareText(context: Context, message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun openSmsApp(context: Context, phone: String, message: String) {
        // Note: not all devices support prefilled SMS body consistently, but most do.
        val uri = Uri.parse("smsto:$phone")
        val intent = Intent(Intent.ACTION_SENDTO, uri).apply {
            putExtra("sms_body", message)
        }
        context.startActivity(intent)
    }
}

