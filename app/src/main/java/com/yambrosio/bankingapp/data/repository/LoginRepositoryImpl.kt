package com.yambrosio.bankingapp.data.repository

import com.yambrosio.bankingapp.data.local.pref.AuthPreferences
import com.yambrosio.bankingapp.data.network.ApiService
import com.yambrosio.bankingapp.data.remote.request.LoginRequest
import com.yambrosio.bankingapp.data.remote.request.otUserModel
import com.yambrosio.bankingapp.data.remote.response.toLogin
import com.yambrosio.bankingapp.data.remote.response.toUserDetail
import com.yambrosio.bankingapp.domain.model.LoginModel
import com.yambrosio.bankingapp.domain.model.UserDetailModel
import com.yambrosio.bankingapp.domain.model.UserModel
import com.yambrosio.bankingapp.domain.model.toUserRequest
import com.yambrosio.bankingapp.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val authPreferences: AuthPreferences
) : LoginRepository {

    override suspend fun doLogin(username: String, password: String): LoginModel {
        try {
            val response = api.doLogin(LoginRequest(username = username, password = password))
            return if (response.isSuccessful) {
                val resultResponse = response.body()
                if (resultResponse != null) {
                    authPreferences.saveAuthToken(resultResponse.token)
                    resultResponse.toLogin()
                } else {
                    throw Exception("Error ${response.errorBody()?.string()}")
                }
            } else {
                throw Exception("Error ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    override suspend fun register(user: UserModel): UserModel {
        try {
            val response = api.register(user.toUserRequest())
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    response.body()!!.otUserModel()
                } else {
                    throw Exception("Error ${response.errorBody()?.string()}")
                }
            } else {
                throw Exception("Error ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("${e.message}")
        }
    }

    override suspend fun getUserById(id: Long): UserDetailModel? {
        val response = api.getIUserById(id)
        return if (response.isSuccessful) response.body()?.let {
            return it.toUserDetail()
        } else null
    }

}