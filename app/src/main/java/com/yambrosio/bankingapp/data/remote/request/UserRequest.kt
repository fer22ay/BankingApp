package com.yambrosio.bankingapp.data.remote.request

data class UserRequest(
    val idTypeEmployee: Long = 0,
    val idCompany: Long = 0,
    val name: String,
    val username: String,
    val password: String,
    val email: String
)
