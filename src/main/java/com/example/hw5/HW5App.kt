package com.example.hw5

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.hw5.database.AppDatabase

class HW5App : Application() {
    private var _database: AppDatabase? = null
    val database get() = requireNotNull(_database)

    override fun onCreate() {
        super.onCreate()
        _database = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                "app_database"
            )
            .build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when (this) {
        is HW5App -> database
        else -> applicationContext.appDatabase
    }