package com.example.hw5.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw5.model.GithubUser

@Database(entities = [GithubUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}