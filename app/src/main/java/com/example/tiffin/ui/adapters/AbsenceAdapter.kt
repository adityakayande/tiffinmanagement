package com.example.tiffin.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tiffin.data.TiffinAbsenceEntity
import com.example.tiffin.databinding.ItemAbsenceBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AbsenceAdapter(
    private val onDelete: (TiffinAbsenceEntity) -> Unit
) : ListAdapter<TiffinAbsenceEntity, AbsenceAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<TiffinAbsenceEntity>() {
        override fun areItemsTheSame(oldItem: TiffinAbsenceEntity, newItem: TiffinAbsenceEntity) =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TiffinAbsenceEntity, newItem: TiffinAbsenceEntity) =
            oldItem == newItem
    }

    inner class VH(private val binding: ItemAbsenceBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("EEE, dd MMM", Locale.getDefault())

        fun bind(item: TiffinAbsenceEntity) {
            // Convert epoch-day back to a Date for display.
            // We stored noon UTC to dodge DST, so multiply by 86400000 ms/day.
            val displayDate = dateFormat.format(Date(item.dateEpochDay * 86_400_000L))
            binding.textAbsenceDate.text = displayDate

            if (item.slot == "LUNCH") {
                binding.textAbsenceSlot.text = "☀️  Lunch"
                binding.textAbsenceSlot.setTextColor(Color.parseColor("#E65100"))  // deep orange
                binding.viewSlotBar.setBackgroundColor(Color.parseColor("#FF9800"))  // orange bar
            } else {
                binding.textAbsenceSlot.text = "🌙  Dinner"
                binding.textAbsenceSlot.setTextColor(Color.parseColor("#4527A0"))  // deep purple
                binding.viewSlotBar.setBackgroundColor(Color.parseColor("#7E57C2"))  // purple bar
            }

            binding.buttonDeleteAbsence.setOnClickListener { onDelete(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemAbsenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
