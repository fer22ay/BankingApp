package com.yambrosio.bankingapp.data.remote.request

import com.yambrosio.bankingapp.domain.model.UserModel

data class UserRequest(
    val idTypeEmployee: Long = 0,
    val idCompany: Long = 0,
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val message: String? = null,
    val error: String? = null
)

fun UserRequest.otUserModel(): UserModel {
    return UserModel(
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