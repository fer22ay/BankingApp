package com.yambrosio.bankingapp.domain.model

import com.yambrosio.bankingapp.data.remote.request.UserRequest

data class UserModel(
    val idTypeEmployee: Long = 0,
    val idCompany: Long = 0,
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val message: String? = null,
    val error: String? = null
)

fun UserModel.toUserRequest(): UserRequest {
    return UserRequest(
        idTypeEmployee = idTypeEmployee,
        idCompany = idCompany,
        name = name,
        username = username,
        password = password,
        email = email,
        message = message,
        error = error
    )
}