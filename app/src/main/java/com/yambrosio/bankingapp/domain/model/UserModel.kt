package com.yambrosio.bankingapp.domain.model

data class UserModel(
    val idTypeEmployee: Long = 0,
    val idCompany: Long = 0,
    val name: String,
    val username: String,
    val password: String,
    val email: String
)