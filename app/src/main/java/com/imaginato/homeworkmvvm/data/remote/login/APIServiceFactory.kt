package com.imaginato.homeworkmvvm.data.remote.login.WebService

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

public class APIServiceFactory {
    private fun providesOkHttpClientBuilder(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        return httpClient
            .addNetworkInterceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                request.method(chain.request().method, chain.request().body)
                request.addHeader("IMSI", "357175048449937")
                request.addHeader("IMEI", "510110406068589")
                request.addHeader(
                    "X-Acc",
                    "eyJhbGciOiJSUzUxMiJ9.eyJDUE4iOiJZIiwibGFzdExvZ2luIjoiMDMgQXByaWwgMjAxOCAxOTozODoxMyIsImxsZXYiOiJIbWFjU0hBNTEyIiwiaXAiOiI5Mi4zOC4xMzAuMTAwIiwiaXNzIjoiYmUqKipvIiwiZm4iOiJCcm8gQmVydGhvIiwiZmNpcyI6IlkiLCJsb2dpbiI6IiIsImZ0IjoiWSIsImZmbiI6Ik4iLCJzZCI6IjE1MjI4MDU0OTA3NTgiL CJwbG4iOiJKR2dMTUlFOHBwcktaRFZJejB6OUZBPT0iLCJmZnQiOiJOIiwidXNydHAiOiJOIiwic2siOiJmMDVjMmRkOGZkYTYzNzYyYmE2NzUwYmQ3OTc3ZTQ0Y2QwM2ZiOTBkNzY3MDU3NDZhZjk4YjgxOGMyMzBhZjIzIiwiZXhwIjoxNTIyODA2M DkwLCJqdGkiOiI3NDlhZDU4Ni01ZTAyLTRmNzItOWE0MC0wYTk4ODBmYzlkNG MiLCJmdGwiOiJOIn0.Buqs4t hUnkAh2v3okFKk8JsJ6V6XEcCpU__BaYNgj7Q8plXEJE1728FL05UvU4DRKO6GFa gUF9MGx2rqO1Fh-viropeVTKu43zyIpfRqi2d4KhwAsEQK7_V5sV08bKBgdIwxY9LUfKE5MOIr33Q2i8gZIcUZCG2SL8SmZX3YOe6CEwt WH9Mp4hoUvo0MNhFSwR8inA1YPsm5TqrCQwj05-3FdhH6lm57CvF8uJOzBE TGxeGaXs0BjN3a4o5lev4qWAa3nS KEQye3IAzrvyeMNTNKA1KsNsIqgVb3ODrI8yXcPvuTNYcV9K9JiMaUKNoL_0OV9THBFwHpbUQqw"
                )

                request.build()
                chain.proceed(request.build())
            }
            .readTimeout(10, TimeUnit.MINUTES)
            .connectTimeout(10, TimeUnit.MINUTES).build()

    }

    fun loginWebService(loginRequest: LoginRequest): LiveData<LoginResponse> {
        val data = MutableLiveData<LoginResponse>()
        var webserviceResponseList: LoginResponse
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(APIURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(providesOkHttpClientBuilder())
                .build()

            //Defining retrofit api service
            val service = retrofit.create(APIService::class.java)
            service.loginRequest(loginRequest).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.d("Repository", "Response::::" + response.body()!!)
                    webserviceResponseList = Gson().fromJson(
                        response.body(),
                        LoginResponse::class.java
                    )
                    data.setValue(webserviceResponseList)
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.d("Repository", "Failed:::")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data

    }
}