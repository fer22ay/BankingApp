package com.yambrosio.bankingapp.ui.auth.login

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yambrosio.bankingapp.core.Resource
import com.yambrosio.bankingapp.domain.auth.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val app: Application,
    private val loginUseCase: LoginUseCase
) : AndroidViewModel(app) {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private fun doLogin(username: String, password: String) {
        loginUseCase(username.uppercase(), password.uppercase()).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        login = result.data,
                        isSuccess = true,
                        isError = false,
                        loading = false
                    )
                }

                is Resource.Error -> {
                    _state.value =
                        _state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isSuccess = false,
                            isError = true,
                            loading = false
                        )
                }

                is Resource.Loading -> {
                    _state.value =
                        _state.value.copy(isSuccess = false, isError = false, loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onLoginChanged(username: String, password: String) {
        _username.value = username
        _password.value = password
        _isLoginEnable.value = enableLogin(username, password)
    }

    private fun enableLogin(username: String, password: String) =
        username.length > 4 && password.length > 6

    fun onDismissDialog() {
        _state.value = _state.value.copy(isError = false)
    }

    fun onLoginSelected() {
        _state.value = _state.value.copy(isError = false, loading = true)
        viewModelScope.launch {
            doLogin(_username.value!!, _password.value!!)
        }
    }
}