package com.example.tiffin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiffin.MainActivity
import com.example.tiffin.R
import com.example.tiffin.data.BackupRestoreUtils
import com.example.tiffin.data.GoogleDriveHelper
import com.example.tiffin.databinding.FragmentCustomerListBinding
import com.example.tiffin.ui.adapters.CustomerAdapter
import com.example.tiffin.ui.dialogs.CustomerDialogFragment
import com.example.tiffin.ui.viewmodel.CustomerListViewModel
import com.example.tiffin.ui.viewmodel.CustomerListViewModelFactory
import com.example.tiffin.TiffinApp
import kotlinx.coroutines.launch

class CustomerListFragment : Fragment() {

    private var binding: FragmentCustomerListBinding? = null
    private val bindingNonNull: FragmentCustomerListBinding
        get() = requireNotNull(binding)

    // ── Local file Export / Import ────────────────────────────────────────────

    private val backupLauncher =
        registerForActivityResult(ActivityResultContracts.CreateDocument("application/json")) { uri ->
            if (uri != null) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val success = BackupRestoreUtils.backupDatabaseJson(requireContext(), uri)
                    Toast.makeText(
                        requireContext(),
                        if (success) "Local backup saved!" else "Backup failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    private val restoreLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val success = BackupRestoreUtils.restoreDatabaseJson(requireContext(), uri)
                    if (!success) Toast.makeText(requireContext(), "Restore failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    // ─────────────────────────────────────────────────────────────────────────

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        return bindingNonNull.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ── Menu ─────────────────────────────────────────────────────────────
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                val signedIn = GoogleDriveHelper.isSignedIn(requireContext())

                if (signedIn) {
                    menu.add(Menu.NONE, MENU_DRIVE_BACKUP,  1, "☁ Backup to Drive")
                    menu.add(Menu.NONE, MENU_DRIVE_RESTORE, 2, "☁ Restore from Drive")
                    menu.add(Menu.NONE, MENU_SIGN_OUT,      3, "Sign Out Google")
                } else {
                    menu.add(Menu.NONE, MENU_SIGN_IN,       1, "Sign in with Google")
                }

                // Local file options always visible
                menu.add(Menu.NONE, MENU_LOCAL_BACKUP,  4, "Export to File")
                menu.add(Menu.NONE, MENU_LOCAL_RESTORE, 5, "Import from File")
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    MENU_SIGN_IN -> {
                        (requireActivity() as MainActivity).launchGoogleSignIn()
                        true
                    }
                    MENU_SIGN_OUT -> {
                        (requireActivity() as MainActivity).signOut()
                        requireActivity().invalidateOptionsMenu()
                        true
                    }
                    MENU_DRIVE_BACKUP -> {
                        viewLifecycleOwner.lifecycleScope.launch {
                            Toast.makeText(requireContext(), "Backing up to Drive…", Toast.LENGTH_SHORT).show()
                            val ok = BackupRestoreUtils.backupToDrive(requireContext())
                            Toast.makeText(
                                requireContext(),
                                if (ok) "✓ Drive backup complete!" else "Drive backup failed",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        true
                    }
                    MENU_DRIVE_RESTORE -> {
                        viewLifecycleOwner.lifecycleScope.launch {
                            Toast.makeText(requireContext(), "Restoring from Drive…", Toast.LENGTH_SHORT).show()
                            val ok = BackupRestoreUtils.restoreFromDrive(requireContext())
                            if (!ok) Toast.makeText(requireContext(), "No Drive backup found or restore failed", Toast.LENGTH_LONG).show()
                        }
                        true
                    }
                    MENU_LOCAL_BACKUP -> {
                        backupLauncher.launch("${BackupRestoreUtils.BACKUP_FILE_NAME}.json")
                        true
                    }
                    MENU_LOCAL_RESTORE -> {
                        restoreLauncher.launch(arrayOf("application/json", "*/*"))
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // ── ViewModel + RecyclerView ──────────────────────────────────────────
        val app = requireActivity().application as TiffinApp
        val viewModel = androidx.lifecycle.ViewModelProvider(
            this,
            CustomerListViewModelFactory(app.customerRepository)
        )[CustomerListViewModel::class.java]

        val adapter = CustomerAdapter { customerId ->
            findNavController().navigate(
                R.id.action_customerList_to_customerDetails,
                androidx.core.os.bundleOf("customerId" to customerId)
            )
        }

        bindingNonNull.recyclerCustomers.layoutManager = LinearLayoutManager(requireContext())
        bindingNonNull.recyclerCustomers.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.customers.collect { list ->
                adapter.submitList(list)
                val emptyLayout = view.findViewById<View>(R.id.layoutEmptyState)
                if (list.isEmpty()) {
                    emptyLayout?.visibility = View.VISIBLE
                    bindingNonNull.recyclerCustomers.visibility = View.GONE
                } else {
                    emptyLayout?.visibility = View.GONE
                    bindingNonNull.recyclerCustomers.visibility = View.VISIBLE
                }
            }
        }

        bindingNonNull.fabAddCustomer.setOnClickListener {
            val dialog = CustomerDialogFragment.newInstance("Add Customer")
            dialog.onSaveCallback = { result ->
                viewModel.addCustomer(
                    name = result.name,
                    phone = result.phone,
                    address = result.address,
                    dateOfJoiningEpochMs = result.dateOfJoiningEpochMs,
                    photoUri = result.photoUri,
                    baseMonthlyFee = result.baseMonthlyFee,
                    tiffinPrice = result.tiffinPrice
                )
            }
            dialog.show(childFragmentManager, "CustomerDialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val MENU_SIGN_IN       = 10
        private const val MENU_SIGN_OUT      = 11
        private const val MENU_DRIVE_BACKUP  = 12
        private const val MENU_DRIVE_RESTORE = 13
        private const val MENU_LOCAL_BACKUP  = 14
        private const val MENU_LOCAL_RESTORE = 15
    }
}
