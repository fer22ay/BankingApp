package com.yambrosio.bankingapp.data.network

import com.yambrosio.bankingapp.data.remote.ApiClient
import com.yambrosio.bankingapp.data.remote.response.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiClient: ApiClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun doLogin(username: String, password: String): Response<LoginResponse> {
        return withContext(dispatcher) {
            apiClient.doLogin(username, password)
        }
    }
}