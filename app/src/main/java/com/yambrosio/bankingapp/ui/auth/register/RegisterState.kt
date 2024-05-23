package com.yambrosio.bankingapp.ui.auth.register

import com.yambrosio.bankingapp.domain.model.UserModel

data class RegisterState(
    val userModel: UserModel,
    val error: String = "",
    val loading: Boolean = false
)