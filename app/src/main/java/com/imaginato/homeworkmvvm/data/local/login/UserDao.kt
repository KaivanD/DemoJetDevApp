package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM login")
    fun getAll(): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}