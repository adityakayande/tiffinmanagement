package com.example.tiffin.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.tiffin.databinding.DialogCustomerBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class CustomerDialogResult(
    val name: String,
    val phone: String,
    val address: String?,
    val dateOfJoiningEpochMs: Long,
    val photoUri: String?,
    val baseMonthlyFee: Long,
    val tiffinPrice: Long
)

class CustomerDialogFragment : DialogFragment() {

    private var _binding: DialogCustomerBinding? = null
    private val binding get() = _binding!!

    private var selectedDate = Calendar.getInstance()
    private var photoUri: Uri? = null

    var initialData: CustomerDialogResult? = null
    var onSaveCallback: ((CustomerDialogResult) -> Unit)? = null

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && photoUri != null) {
            binding.imageCustomerPhoto.setImageURI(photoUri)
        } else {
            photoUri = null
        }
    }

    private fun createTempPhotoUri(): Uri {
        val tempFile = java.io.File.createTempFile("customer_photo_", ".jpg", requireContext().cacheDir)
        return androidx.core.content.FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            tempFile
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCustomerBinding.inflate(LayoutInflater.from(requireContext()))
        
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        initialData?.let { initial ->
            binding.inputName.setText(initial.name)
            binding.inputPhone.setText(initial.phone)
            binding.inputAddress.setText(initial.address.orEmpty())
            selectedDate.timeInMillis = initial.dateOfJoiningEpochMs
            if (initial.photoUri != null) {
                try {
                    photoUri = Uri.parse(initial.photoUri)
                    binding.imageCustomerPhoto.setImageURI(photoUri)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        binding.inputJoinDate.setText(dateFormat.format(selectedDate.time))
        binding.inputJoinDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    selectedDate.set(Calendar.YEAR, year)
                    selectedDate.set(Calendar.MONTH, month)
                    selectedDate.set(Calendar.DAY_OF_MONTH, day)
                    binding.inputJoinDate.setText(dateFormat.format(selectedDate.time))
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.imageCustomerPhoto.setOnClickListener {
            val uri = createTempPhotoUri()
            photoUri = uri
            takePicture.launch(uri)
        }

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(arguments?.getString("title") ?: "Customer")
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ ->
                val name = binding.inputName.text?.toString()?.trim().orEmpty()
                val phone = binding.inputPhone.text?.toString()?.trim().orEmpty()
                val address = binding.inputAddress.text?.toString()?.trim().orEmpty().ifEmpty { null }
                
                onSaveCallback?.invoke(
                    CustomerDialogResult(
                        name = name,
                        phone = phone,
                        address = address,
                        dateOfJoiningEpochMs = selectedDate.timeInMillis,
                        photoUri = photoUri?.toString(),
                        baseMonthlyFee = initialData?.baseMonthlyFee ?: 0L,
                        tiffinPrice = initialData?.tiffinPrice ?: 0L
                    )
                )
            }
            .setNegativeButton("Cancel", null)
            .create()

        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(title: String): CustomerDialogFragment {
            val frag = CustomerDialogFragment()
            frag.arguments = Bundle().apply {
                putString("title", title)
            }
            return frag
        }
    }
}
