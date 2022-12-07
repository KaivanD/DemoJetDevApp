package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class User constructor(
    @PrimaryKey
    val userId: String,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "isDeleted") var isDeleted: Boolean
)