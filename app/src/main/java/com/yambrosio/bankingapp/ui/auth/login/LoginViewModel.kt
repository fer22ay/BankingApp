package com.yambrosio.bankingapp.ui.auth.login

import androidx.lifecycle.ViewModel
import com.yambrosio.bankingapp.domain.auth.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

}