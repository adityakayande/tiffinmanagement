package com.example.tiffin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tiffin.data.PaymentEntity
import com.example.tiffin.databinding.ItemPaymentBinding
import com.example.tiffin.domain.DueCalculator

class PaymentAdapter(
    private val onShare: (PaymentEntity) -> Unit,
    private val onEdit: (PaymentEntity) -> Unit,
    private val onDelete: (PaymentEntity) -> Unit
) : ListAdapter<PaymentEntity, PaymentAdapter.VH>(Diff) {


    private val dateFormat = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())

    object Diff : DiffUtil.ItemCallback<PaymentEntity>() {
        override fun areItemsTheSame(oldItem: PaymentEntity, newItem: PaymentEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PaymentEntity, newItem: PaymentEntity): Boolean =
            oldItem == newItem
    }

    inner class VH(private val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PaymentEntity) {
            val due = DueCalculator.calculate(item.amountToPay, item.amountPaid)
            
            if (item.startDateMs > 0) {
                val startStr = dateFormat.format(item.startDateMs)
                val endStr = dateFormat.format(item.endDateMs)
                binding.textMonthYear.text = "$startStr to $endStr"
            } else {
                val monthNames = arrayOf("", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
                val monthStr = if (item.month in 1..12) monthNames[item.month] else item.month.toString()
                binding.textMonthYear.text = "$monthStr ${item.year} (Legacy)"
            }            
            binding.textAmounts.text = "To pay: ₹${item.amountToPay}  Paid: ₹${item.amountPaid}  Due: ₹${due.dueAmount}"
            binding.textStatus.text = due.status.name

            binding.buttonShare.setOnClickListener { onShare(item) }
            binding.buttonEdit.setOnClickListener { onEdit(item) }
            binding.buttonDelete.setOnClickListener { onDelete(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

