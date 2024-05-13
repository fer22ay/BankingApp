package com.yambrosio.bankingapp.data.remote

import com.yambrosio.bankingapp.data.remote.request.LoginRequest
import com.yambrosio.bankingapp.data.remote.request.UserRequest
import com.yambrosio.bankingapp.data.remote.response.LoginResponse
import com.yambrosio.bankingapp.data.remote.response.UserDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiClient {
    @POST("login")
    suspend fun doLogin(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("create")
    suspend fun createUser(@Body userRequest: UserRequest): Response<UserRequest?>

    @POST("list/users")
    suspend fun listUsers(@Header("Authorization") token: String?): Response<LoginResponse>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Long): Response<UserDetailResponse?>
}