package com.yambrosio.bankingapp.domain.auth.login

import android.util.Log
import com.yambrosio.bankingapp.core.NetworkHelper
import com.yambrosio.bankingapp.core.Resource
import com.yambrosio.bankingapp.domain.model.LoginModel
import com.yambrosio.bankingapp.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val networkHelper: NetworkHelper
) {
    operator fun invoke(username: String, password: String): Flow<Resource<LoginModel>> = flow {
        try {
            emit(Resource.Loading())
            if (networkHelper.isNetworkConnected()) {
                val loginResponse = repository.doLogin(username, password)
                emit(Resource.Success(loginResponse))
            } else
                emit(Resource.Error("No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (t: Throwable) {
            when (t) {
                is IOException -> emit(Resource.Error("Couldn't reach server. Check your internet connection"))
                else -> emit(Resource.Error(t.message.toString()))
            }
        }
    }

}