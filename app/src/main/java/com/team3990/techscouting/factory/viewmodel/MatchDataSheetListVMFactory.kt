package com.team3990.techscouting.factory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.team3990.techscouting.repository.DataSheetRepository
import com.team3990.techscouting.ui.dataSheet.MatchDataSheetListViewModel

class MatchDataSheetListVMFactory(
    private val dataSheetRepository: DataSheetRepository
) : ViewModelProvider.Factory {

    /**
     * Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or an activity),
     * associated with this ViewModelProvider.
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchDataSheetListViewModel::class.java))
            return MatchDataSheetListViewModel(dataSheetRepository) as T
        else
            throw IllegalArgumentException()
    }

}