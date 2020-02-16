package com.team3990.techscouting.repository

import androidx.lifecycle.LiveData
import com.team3990.techscouting.database.dao.MatchDataDao
import com.team3990.techscouting.model.MatchData

class DataSheetRepository(
    private val matchDataDao: MatchDataDao
) {

    fun getMatchDataSheet() : LiveData<Array<MatchData>> = matchDataDao.getAllMatchDataSheet()

    fun getMatchDataSheetForRegional(regional: String) : LiveData<Array<MatchData>> = matchDataDao.getMatchDataSheetByRegional(regional)

}