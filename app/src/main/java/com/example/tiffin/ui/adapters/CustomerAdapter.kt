package com.example.tiffin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tiffin.data.CustomerListItem
import com.example.tiffin.databinding.ItemCustomerBinding
import com.example.tiffin.domain.DueCalculator

class CustomerAdapter(
    private val onClick: (customerId: Long) -> Unit
) : ListAdapter<CustomerListItem, CustomerAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<CustomerListItem>() {
        override fun areItemsTheSame(oldItem: CustomerListItem, newItem: CustomerListItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CustomerListItem, newItem: CustomerListItem): Boolean =
            oldItem == newItem
    }

    inner class VH(private val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CustomerListItem) {
            binding.textName.text = item.name
            binding.textPhone.text = item.phone
            binding.textTotalDue.text = "₹${item.totalDue}"
            binding.textStatus.text = DueCalculator.calculateQuickCustomerStatus(item.totalDue).name

            if (!item.photoUri.isNullOrEmpty()) {
                try {
                    binding.imageAvatar.setImageURI(android.net.Uri.parse(item.photoUri))
                    binding.textAvatarInitial.visibility = android.view.View.GONE
                } catch (e: Exception) {
                    binding.imageAvatar.setImageResource(android.R.color.transparent)
                    binding.textAvatarInitial.visibility = android.view.View.VISIBLE
                    binding.textAvatarInitial.text = item.name.take(1).uppercase()
                }
            } else {
                binding.imageAvatar.setImageResource(android.R.color.transparent)
                binding.textAvatarInitial.visibility = android.view.View.VISIBLE
                binding.textAvatarInitial.text = item.name.take(1).uppercase()
            }

            binding.root.setOnClickListener { onClick(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

