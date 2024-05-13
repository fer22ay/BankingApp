package com.yambrosio.bankingapp.domain.model

data class LoginModel(
    val id: Long = 0,
    val message: String? = null,
    val username: String? = null,
    val token: String? = null,
    val error: String? = null
)
