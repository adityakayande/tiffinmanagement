package com.example.tiffin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tiffin.data.BackupRestoreUtils
import com.example.tiffin.data.GoogleDriveHelper
import com.example.tiffin.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        const val RC_SIGN_IN = 1001
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // Configure Google Sign-In client
        googleSignInClient = GoogleSignIn.getClient(this, GoogleDriveHelper.getSignInOptions())

        // QR Code Icon Click Listener
        val qrIcon = binding.toolbar.findViewById<android.widget.ImageView>(R.id.icon_qr)
        qrIcon?.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_qr, null)
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Close", null)
                .show()
        }
    }

    /** Called from CustomerListFragment when the user taps "Sign in with Google". */
    fun launchGoogleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    /** Called from CustomerListFragment when the user taps "Sign out". */
    fun signOut() {
        googleSignInClient.signOut().addOnCompleteListener {
            Toast.makeText(this, "Signed out from Google", Toast.LENGTH_SHORT).show()
            // Refresh the menu to reflect sign-out state
            invalidateOptionsMenu()
        }
    }

    @Deprecated("Using onActivityResult for GoogleSignIn which still requires it")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                task.getResult(ApiException::class.java) // throws if failed
                Toast.makeText(this, "Signed in successfully!", Toast.LENGTH_SHORT).show()
                invalidateOptionsMenu()

                // Offer to restore from Drive if this looks like a fresh install
                val db = (application as TiffinApp).database
                lifecycleScope.launch {
                    val customerCount = db.customerDao().getAllCustomers().size
                    if (customerCount == 0) {
                        tryRestoreOnFreshInstall()
                    } else {
                        // Auto backup current data to Drive silently
                        BackupRestoreUtils.backupToDrive(this@MainActivity)
                    }
                }
            } catch (e: ApiException) {
                Log.e(TAG, "Google sign-in failed: ${e.statusCode}", e)
                Toast.makeText(this, "Sign-in failed. Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun tryRestoreOnFreshInstall() {
        val json = com.example.tiffin.data.GoogleDriveHelper.downloadBackup(this)
        if (json != null) {
            runOnUiThread {
                androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Backup Found!")
                    .setMessage("A previous Tiffin backup was found in your Google Drive. Would you like to restore it now?")
                    .setPositiveButton("Restore") { _, _ ->
                        lifecycleScope.launch {
                            val ok = BackupRestoreUtils.restoreFromDrive(this@MainActivity)
                            if (!ok) Toast.makeText(this@MainActivity, "Restore failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("Skip", null)
                    .show()
            }
        }
    }
}
