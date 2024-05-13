package com.yambrosio.bankingapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.yambrosio.bankingapp.domain.model.LoginModel

data class LoginResponse(
    @SerializedName("idUser")
    val id: Long = 0,
    @SerializedName("message")
    val message: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("error")
    val error: String? = null
)

fun LoginResponse.toLogin(): LoginModel {
    return LoginModel(
        id = id,
        message = message,
        username = username,
        token = token,
        error = error
    )
}