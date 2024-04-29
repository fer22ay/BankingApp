package com.yambrosio.bankingapp.ui.auth.register

import androidx.lifecycle.ViewModel
import com.yambrosio.bankingapp.domain.auth.register.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase) :
    ViewModel() {
}