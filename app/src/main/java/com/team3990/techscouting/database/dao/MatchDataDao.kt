package com.team3990.techscouting.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.team3990.techscouting.model.MatchData

@Dao
interface MatchDataDao {

    @Insert(onConflict = REPLACE)
    fun save(data: MatchData)

    @Query("SELECT * FROM matchdata")
    fun getAllMatchDataSheet() : LiveData<Array<MatchData>>

    @Query("SELECT * FROM matchdata WHERE regionalName = :regional")
    fun getMatchDataSheetByRegional(regional: String) : LiveData<Array<MatchData>>

}