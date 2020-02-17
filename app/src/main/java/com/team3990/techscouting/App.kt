package com.team3990.techscouting

import android.app.Application
import android.os.AsyncTask
import androidx.room.Room
import com.team3990.techscouting.common.interfaces.DataSheet
import com.team3990.techscouting.database.AppDatabase
import com.team3990.techscouting.factory.RepositoryFactory
import com.team3990.techscouting.model.MatchData
import com.team3990.techscouting.util.Mobility
import java.util.*

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
        lateinit var repositoryFactory: RepositoryFactory
    }

    /** Application's lifecycle */

    override fun onCreate() {
        super.onCreate()

        // Initialize the database instance.
        database = Room.databaseBuilder(this, AppDatabase::class.java, "tech-scouting-database")
            .fallbackToDestructiveMigration()
            .build()

        // Initialize the repository factory instance.
        repositoryFactory = RepositoryFactory()

        AsyncTask.execute {
            val dummyMatchData = MatchData(1, "Quals 2", Date(), "Samuel Proulx", "Sherbrooke")
            val dummyMatchData2 = MatchData(2, "Quals 3", Date(), "Nardo Jean-Gilles", "Sherbrooke")

            database.matchDataDao().save(dummyMatchData)
            database.matchDataDao().save(dummyMatchData2)
        }
    }

}