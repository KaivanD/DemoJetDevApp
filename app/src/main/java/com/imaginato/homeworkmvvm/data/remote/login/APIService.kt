package com.imaginato.homeworkmvvm.data.remote.login.WebService

import com.google.gson.JsonObject
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("api/login")
    fun loginRequest(@Body loginRequest: LoginRequest): Call<JsonObject>
}