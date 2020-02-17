package com.team3990.techscouting.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.team3990.techscouting.database.converter.DateConverter
import com.team3990.techscouting.database.converter.EndgameConverter
import com.team3990.techscouting.database.converter.MobilityConverter
import com.team3990.techscouting.database.dao.MatchDataDao
import com.team3990.techscouting.model.MatchData

/**
 * The database holder serving as the main access point for the
 * underlying connection to the app's persisted data.
 *
 * @author Anas Merbouh
 * @version 1.0
 * */
@Database(entities = [MatchData::class], version = 3)
@TypeConverters(value = [DateConverter::class, EndgameConverter::class, MobilityConverter::class])
abstract class AppDatabase : RoomDatabase() {

    /**
     * Returns the class containing the methods used
     * to access the persisted data sheets.
     * */
    abstract fun matchDataDao() : MatchDataDao

}