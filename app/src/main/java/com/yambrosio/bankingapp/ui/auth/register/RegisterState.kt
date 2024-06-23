package com.yambrosio.bankingapp.ui.auth.register

import com.yambrosio.bankingapp.domain.model.UserModel

data class RegisterState(
    val userModel: UserModel? = null,
    val error: String = "",
    val isSuccess: Boolean = false,
    val loading: Boolean = false,
    val isError: Boolean = false
)