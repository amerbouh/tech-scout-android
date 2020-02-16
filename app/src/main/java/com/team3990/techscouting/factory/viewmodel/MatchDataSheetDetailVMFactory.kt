package com.team3990.techscouting.factory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.team3990.techscouting.ui.dataSheet.MatchDataSheetDetailViewModel

class MatchDataSheetDetailVMFactory : ViewModelProvider.Factory {

    /**
     * Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or an activity),
     * associated with this ViewModelProvider.
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchDataSheetDetailViewModel::class.java))
            return MatchDataSheetDetailViewModel() as T
        else
            throw IllegalArgumentException()
    }

}