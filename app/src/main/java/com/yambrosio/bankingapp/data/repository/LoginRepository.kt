package com.yambrosio.bankingapp.data.repository

import com.yambrosio.bankingapp.data.network.ApiService
import com.yambrosio.bankingapp.data.remote.response.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: ApiService) {
    suspend fun doLogin(username: String, password: String) : Response<LoginResponse> {
        return api.doLogin(username, password)
    }
}