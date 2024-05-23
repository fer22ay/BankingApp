package com.yambrosio.bankingapp.domain.auth.register

import com.yambrosio.bankingapp.core.NetworkHelper
import com.yambrosio.bankingapp.core.Resource
import com.yambrosio.bankingapp.domain.model.LoginModel
import com.yambrosio.bankingapp.domain.model.UserModel
import com.yambrosio.bankingapp.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val repository: LoginRepository
) {
    operator fun invoke(
        typeEmployee: Long,
        company: Long,
        name: String,
        username: String,
        password: String,
        email: String
    ): Flow<Resource<UserModel>> = flow {
        try {
            emit(Resource.Loading())
            if (networkHelper.isNetworkConnected()) {
                val registerResponse = repository.register(user = UserModel(idTypeEmployee = typeEmployee, idCompany = company,
                    name = name, username = username, password = password, email = email))
                emit(Resource.Success(registerResponse))
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