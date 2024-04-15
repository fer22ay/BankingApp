package com.yambrosio.bankingapp.data.network

import com.yambrosio.bankingapp.data.remote.ApiClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiClient: ApiClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun doLogin(username: String, password: String) = apiClient.doLogin(username, password)

}