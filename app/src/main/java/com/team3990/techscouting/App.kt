package com.team3990.techscouting

import android.app.Application
import android.os.AsyncTask
import androidx.room.Room
import com.team3990.techscouting.database.AppDatabase
import com.team3990.techscouting.factory.RepositoryFactory
import com.team3990.techscouting.model.MatchData
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
    }

}