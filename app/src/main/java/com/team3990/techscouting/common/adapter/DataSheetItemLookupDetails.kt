package com.team3990.techscouting.common.adapter

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.team3990.techscouting.view.DataSheetHolder

class DataSheetItemLookupDetails(
    private val recyclerView: RecyclerView
) : ItemDetailsLookup<Long>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)

        // Return the details of the recycler view item corresponding to the
        // selected view, if applicable.
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as DataSheetHolder).getItemDetails()
        }

        return null
    }

}