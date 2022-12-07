package com.imaginato.homeworkmvvm.data.remote.login.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("password")
    val password: String? = null,

    @SerializedName("username")
    val username: String? = null
)
