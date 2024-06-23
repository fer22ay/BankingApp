package com.yambrosio.bankingapp.ui.auth.register

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yambrosio.bankingapp.core.Resource
import com.yambrosio.bankingapp.domain.auth.register.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val app: Application,
    private val addUserUseCase: AddUserUseCase
) : AndroidViewModel(app) {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    private fun doRegister(fullName: String, username: String, password: String, email: String) {
        addUserUseCase(
            fullName.uppercase(),
            username.uppercase(),
            password.uppercase(),
            email
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(userModel = result.data, isSuccess = true, isError = false, loading = false)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "An unexpected error occurred",
                        isSuccess = false,
                        loading = false,
                        isError = true
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isSuccess = false, isError = false, loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onRegisterChanged(
        name: String, username: String, password: String, confirmPassword: String, email: String
    ) {
        _name.value = name
        _username.value = username
        _password.value = password
        _confirmPassword.value = confirmPassword
        _email.value = email
        _isButtonEnable.value = enableLogin(name, username, password, confirmPassword, email)
    }

    private fun enableLogin(
        name: String,
        username: String,
        password: String,
        confirmPassword: String,
        email: String
    ) =
        name.isNotBlank() && username.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() && password.length > 6 && password == confirmPassword

    fun onDismissDialog() {
        _state.value = _state.value.copy(isError = false)
    }

    fun onLoginSelected() {
        _state.value = _state.value.copy(isError = false, loading = true)
        viewModelScope.launch {
            doRegister(
                _name.value!!,
                _username.value!!,
                _password.value!!,
                _email.value!!
            )
        }
    }
}