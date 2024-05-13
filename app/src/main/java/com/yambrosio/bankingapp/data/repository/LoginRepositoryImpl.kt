package com.yambrosio.bankingapp.data.repository

import com.yambrosio.bankingapp.data.local.pref.AuthPreferences
import com.yambrosio.bankingapp.data.network.ApiService
import com.yambrosio.bankingapp.data.remote.request.LoginRequest
import com.yambrosio.bankingapp.data.remote.request.UserRequest
import com.yambrosio.bankingapp.data.remote.request.otUser
import com.yambrosio.bankingapp.data.remote.response.toLogin
import com.yambrosio.bankingapp.data.remote.response.toUserDetail
import com.yambrosio.bankingapp.domain.model.LoginModel
import com.yambrosio.bankingapp.domain.model.UserDetailModel
import com.yambrosio.bankingapp.domain.model.UserModel
import com.yambrosio.bankingapp.domain.repository.LoginRepository
import retrofit2.HttpException
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

    override suspend fun register(user: UserModel): UserModel? {
        val response = api.register(
            userRequest = UserRequest(
                idTypeEmployee = user.idTypeEmployee,
                idCompany = user.idCompany,
                name = user.name,
                username = user.username,
                password = user.password,
                email = user.email
            )
        )
        return if (response.isSuccessful) response.body()?.let { resultResponse ->
            return resultResponse.otUser()
        } else null
    }

    override suspend fun getUserById(id: Long): UserDetailModel? {
        val response = api.getIUserById(id)
        return if (response.isSuccessful) response.body()?.let {
            return it.toUserDetail()
        } else null
    }

}