package com.example.hw5.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw5.model.GithubUser

@Dao
interface UserDao {
    @Query("SELECT * FROM GithubUser")
    suspend fun getUsers(): List<GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<GithubUser>)
}