package com.yambrosio.bankingapp.domain.repository

import com.yambrosio.bankingapp.domain.model.LoginModel
import com.yambrosio.bankingapp.domain.model.UserDetailModel
import com.yambrosio.bankingapp.domain.model.UserModel

interface LoginRepository {
    suspend fun doLogin(username: String, password: String): LoginModel
    suspend fun register(user: UserModel): UserModel?
    suspend fun getUserById(id: Long): UserDetailModel?
}