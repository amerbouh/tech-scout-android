package com.team3990.techscouting.factory

import com.team3990.techscouting.database.dao.MatchDataDao
import com.team3990.techscouting.repository.DataSheetRepository

class RepositoryFactory {

    fun provideDataSheetRepository(matchDataDao: MatchDataDao) : DataSheetRepository = DataSheetRepository(matchDataDao)

}