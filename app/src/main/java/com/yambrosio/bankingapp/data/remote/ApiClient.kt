package com.yambrosio.bankingapp.data.remote

import com.yambrosio.bankingapp.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @POST("/api/v1/login")
    suspend fun doLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<UserResponse>
}