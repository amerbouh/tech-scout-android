package com.team3990.techscouting.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.team3990.techscouting.R
import com.team3990.techscouting.common.interfaces.DataSheet
import com.team3990.techscouting.databinding.HolderDataSheetBinding
import com.team3990.techscouting.view.DataSheetHolder

class DataSheetAdapter : RecyclerView.Adapter<DataSheetHolder>() {

    /** Properties */

    private var dataSheets: Array<DataSheet> = arrayOf()

    /** Methods */

    override fun getItemCount(): Int = dataSheets.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataSheetHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // Get an instance of the holder's binding.
        val binding = DataBindingUtil.inflate<HolderDataSheetBinding>(layoutInflater, R.layout.holder_data_sheet, parent, false)

        return DataSheetHolder(binding)
    }

    override fun onBindViewHolder(holder: DataSheetHolder, position: Int) {
        holder.binding.datasheet = dataSheets[position]
    }

    fun setDataSheets(sheets: Array<DataSheet>) {
        this.dataSheets = sheets
        this.notifyDataSetChanged()
    }

}