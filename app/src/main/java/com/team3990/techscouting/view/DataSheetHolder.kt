package com.team3990.techscouting.view

import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.team3990.techscouting.databinding.HolderDataSheetBinding

class DataSheetHolder(
    val binding: HolderDataSheetBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> = object : ItemDetailsLookup.ItemDetails<Long>() {
        override fun getPosition(): Int = adapterPosition
        override fun getSelectionKey(): Long? = itemId
    }

}