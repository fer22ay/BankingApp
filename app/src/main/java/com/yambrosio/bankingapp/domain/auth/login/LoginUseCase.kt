package com.yambrosio.bankingapp.domain.auth.login

import com.yambrosio.bankingapp.data.remote.response.LoginResponse
import com.yambrosio.bankingapp.data.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(username: String, password: String): Response<LoginResponse> {
        return repository.doLogin(username, password)
    }
}