package com.team3990.techscouting

import android.app.Application
import androidx.room.Room
import com.team3990.techscouting.database.AppDatabase
import com.team3990.techscouting.factory.RepositoryFactory

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