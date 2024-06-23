package com.yambrosio.bankingapp.ui.auth.login

import com.yambrosio.bankingapp.domain.model.LoginModel

data class LoginState(
    val login: LoginModel? = null,
    val error: String = "",
    val isSuccess: Boolean = false,
    val loading: Boolean = false,
    val isError: Boolean = false
)