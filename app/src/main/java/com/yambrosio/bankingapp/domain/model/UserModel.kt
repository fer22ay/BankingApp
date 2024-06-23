package com.yambrosio.bankingapp.domain.model

import com.yambrosio.bankingapp.data.remote.request.UserRequest

data class UserModel(
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val message: String? = null,
    val error: String? = null
)

fun UserModel.toUserRequest(): UserRequest {
    return UserRequest(
        name = name,
        username = username,
        password = password,
        email = email,
        message = message,
        error = error
    )
}