package com.imaginato.homeworkmvvm.data.remote.login.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("data")
    val data: Data? = null,

    @SerializedName("errorMessage")
    val errorMessage: String? = null,

    @SerializedName("errorCode")
    val errorCode: String? = null
)

data class Data(

    @SerializedName("isDeleted")
    val isDeleted: Boolean? = null,

    @SerializedName("userName")
    val userName: String? = null,

    @SerializedName("userId")
    val userId: String? = null
)
