package com.yambrosio.bankingapp.data.network

import com.yambrosio.bankingapp.data.remote.ApiClient
import com.yambrosio.bankingapp.data.remote.request.LoginRequest
import com.yambrosio.bankingapp.data.remote.request.UserRequest
import com.yambrosio.bankingapp.data.remote.response.CompanyResponse
import com.yambrosio.bankingapp.data.remote.response.LoginResponse
import com.yambrosio.bankingapp.data.remote.response.TypeEmployeeResponse
import com.yambrosio.bankingapp.data.remote.response.UserDetailResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiClient: ApiClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun doLogin(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(dispatcher) {
            apiClient.doLogin(loginRequest)
        }
    }

    suspend fun register(userRequest: UserRequest): Response<UserRequest> {
        return withContext(dispatcher) {
            apiClient.createUser(userRequest)
        }
    }

    suspend fun getIUserById(id: Long): Response<UserDetailResponse?> {
        return withContext(dispatcher) {
            apiClient.getUserById(id)
        }
    }

    suspend fun getCompanies(token: String): Response<List<CompanyResponse>> {
        return withContext(dispatcher) {
            apiClient.listCompanies(token)
        }
    }

    suspend fun getTypeEmployees(token: String): Response<List<TypeEmployeeResponse>> {
        return withContext(dispatcher) {
            apiClient.listTypeEmployees(token)
        }
    }

}