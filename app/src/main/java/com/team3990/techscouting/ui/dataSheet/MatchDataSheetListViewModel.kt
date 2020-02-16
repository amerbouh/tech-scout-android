package com.team3990.techscouting.ui.dataSheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.team3990.techscouting.common.interfaces.DataSheet
import com.team3990.techscouting.repository.DataSheetRepository

class MatchDataSheetListViewModel(
    matchDataSheetRepository: DataSheetRepository
) : ViewModel() {

    val dataSheets: LiveData<Array<DataSheet>> = matchDataSheetRepository.getMatchDataSheet() as LiveData<Array<DataSheet>>

}
