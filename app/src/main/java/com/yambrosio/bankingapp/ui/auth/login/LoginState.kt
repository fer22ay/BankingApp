package com.yambrosio.bankingapp.ui.auth.login

import com.yambrosio.bankingapp.domain.model.LoginModel

data class LoginState(
    val login: LoginModel? = null,
    val error: String = ""
)