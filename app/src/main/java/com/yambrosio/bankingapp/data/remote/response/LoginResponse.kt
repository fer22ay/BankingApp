package com.yambrosio.bankingapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("idUser")
    val id: Long = 0,
    @SerializedName("username")
    val username: String,
    @SerializedName("token")
    val token: String
)