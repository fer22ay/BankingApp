package com.yambrosio.bankingapp.data.remote

import com.yambrosio.bankingapp.data.remote.request.UserRequest
import com.yambrosio.bankingapp.data.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @POST("/login")
    suspend fun doLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<LoginResponse>

    @POST("/create")
    suspend fun createUser(@Body userRequest: UserRequest): Response<LoginResponse>

}