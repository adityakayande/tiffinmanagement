package com.example.tiffin.data

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.api.services.drive.model.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object GoogleDriveHelper {

    private const val BACKUP_FILE_NAME = "TiffinBackup.json"
    private const val MIME_JSON = "application/json"

    /** Returns a configured sign-in options object requesting Drive AppData access. */
    fun getSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(DriveScopes.DRIVE_APPDATA))
            .build()
    }

    /** Returns true if the user is already signed in with Drive access. */
    fun isSignedIn(context: Context): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context) ?: return false
        return GoogleSignIn.hasPermissions(account, Scope(DriveScopes.DRIVE_APPDATA))
    }

    /** Builds a Drive service from the currently signed-in account. */
    private fun buildDriveService(context: Context, account: GoogleSignInAccount): Drive {
        val credential = GoogleAccountCredential.usingOAuth2(
            context, listOf(DriveScopes.DRIVE_APPDATA)
        )
        credential.selectedAccount = account.account
        return Drive.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            credential
        )
            .setApplicationName("Tiffin")
            .build()
    }

    /**
     * Uploads the JSON string as TiffinBackup.json to the Drive AppData folder.
     * If a previous backup exists it is replaced.
     */
    suspend fun uploadBackup(context: Context, jsonContent: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val account = GoogleSignIn.getLastSignedInAccount(context)
                    ?: return@withContext false
                val driveService = buildDriveService(context, account)

                val existingId = findBackupFileId(driveService)
                val contentStream = ByteArrayInputStream(jsonContent.toByteArray(Charsets.UTF_8))
                val mediaContent = com.google.api.client.http.InputStreamContent(MIME_JSON, contentStream)

                if (existingId != null) {
                    // Update existing file
                    driveService.files().update(existingId, null, mediaContent).execute()
                } else {
                    // Create new file in AppData folder
                    val metadata = File().apply {
                        name = BACKUP_FILE_NAME
                        parents = listOf("appDataFolder")
                    }
                    driveService.files().create(metadata, mediaContent)
                        .setFields("id")
                        .execute()
                }
                true
            } catch (e: Exception) {
                android.util.Log.e("DriveHelper", "Upload failed", e)
                false
            }
        }
    }

    /**
     * Downloads TiffinBackup.json from Drive AppData folder.
     * Returns null if no backup exists.
     */
    suspend fun downloadBackup(context: Context): String? {
        return withContext(Dispatchers.IO) {
            try {
                val account = GoogleSignIn.getLastSignedInAccount(context)
                    ?: return@withContext null
                val driveService = buildDriveService(context, account)

                val fileId = findBackupFileId(driveService) ?: return@withContext null
                val outputStream = ByteArrayOutputStream()
                driveService.files().get(fileId).executeMediaAndDownloadTo(outputStream)
                outputStream.toString(Charsets.UTF_8.name())
            } catch (e: Exception) {
                android.util.Log.e("DriveHelper", "Download failed", e)
                null
            }
        }
    }

    private fun findBackupFileId(driveService: Drive): String? {
        val result = driveService.files().list()
            .setSpaces("appDataFolder")
            .setQ("name = '$BACKUP_FILE_NAME'")
            .setFields("files(id, name)")
            .execute()
        return result.files.firstOrNull()?.id
    }
}
