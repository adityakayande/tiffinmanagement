package com.example.tiffin.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.system.exitProcess

data class BackupData(
    val customers: List<CustomerEntity>,
    val payments: List<PaymentEntity>,
    val absences: List<TiffinAbsenceEntity>
)

object BackupRestoreUtils {

    const val BACKUP_FILE_NAME = "TiffinBackup"
    private val gson: Gson = GsonBuilder().create()

    // ─────────────────────────────────────────────────────────────────────────
    // Helpers shared by local and Drive paths
    // ─────────────────────────────────────────────────────────────────────────

    /** Reads all data from Room and serialises it to a JSON string. */
    suspend fun buildJsonString(context: Context): String = withContext(Dispatchers.IO) {
        val db = AppDatabase.getInstance(context)
        val data = BackupData(
            customers = db.customerDao().getAllCustomers(),
            payments  = db.paymentDao().getAllPayments(),
            absences  = db.tiffinAbsenceDao().getAllAbsences()
        )
        gson.toJson(data)
    }

    /** Parses a JSON string and writes all data into Room, then restarts the app. */
    suspend fun restoreFromJsonString(context: Context, jsonString: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val backupData = gson.fromJson(jsonString, BackupData::class.java)
                if (backupData?.customers == null) return@withContext false

                val db = AppDatabase.getInstance(context)
                db.customerDao().deleteAllCustomers()
                db.paymentDao().deleteAllPayments()
                db.tiffinAbsenceDao().deleteAllAbsences()

                db.customerDao().insertAllCustomers(backupData.customers)
                db.paymentDao().insertAllPayments(backupData.payments)
                db.tiffinAbsenceDao().insertAllAbsences(backupData.absences)

                withContext(Dispatchers.Main) { restartApp(context) }
                true
            } catch (e: Exception) {
                Log.e("BackupRestore", "restoreFromJsonString failed", e)
                false
            }
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Local file (SAF) export / import  (keep existing behaviour)
    // ─────────────────────────────────────────────────────────────────────────

    suspend fun backupDatabaseJson(context: Context, uri: Uri): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val json = buildJsonString(context)
                context.contentResolver.openOutputStream(uri)?.use { os ->
                    OutputStreamWriter(os).use { it.write(json) }
                }
                true
            } catch (e: Exception) {
                Log.e("BackupRestore", "Local backup failed", e)
                false
            }
        }
    }

    suspend fun restoreDatabaseJson(context: Context, uri: Uri): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                var json = ""
                context.contentResolver.openInputStream(uri)?.use { is_ ->
                    InputStreamReader(is_).use { json = it.readText() }
                }
                restoreFromJsonString(context, json)
            } catch (e: Exception) {
                Log.e("BackupRestore", "Local restore failed", e)
                false
            }
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Google Drive sync
    // ─────────────────────────────────────────────────────────────────────────

    /** Uploads current DB to Google Drive. Returns true on success. */
    suspend fun backupToDrive(context: Context): Boolean {
        return try {
            val json = buildJsonString(context)
            GoogleDriveHelper.uploadBackup(context, json)
        } catch (e: Exception) {
            Log.e("BackupRestore", "Drive backup failed", e)
            false
        }
    }

    /**
     * Downloads the latest backup from Google Drive and restores it into the local DB.
     * Returns true if data was found and restored, false if no remote backup exists.
     */
    suspend fun restoreFromDrive(context: Context): Boolean {
        return try {
            val json = GoogleDriveHelper.downloadBackup(context) ?: return false
            restoreFromJsonString(context, json)
        } catch (e: Exception) {
            Log.e("BackupRestore", "Drive restore failed", e)
            false
        }
    }

    // ─────────────────────────────────────────────────────────────────────────

    private fun restartApp(context: Context) {
        val pm     = context.packageManager
        val intent = pm.getLaunchIntentForPackage(context.packageName)
        val restart = Intent.makeRestartActivityTask(intent?.component)
        context.startActivity(restart)
        exitProcess(0)
    }
}
