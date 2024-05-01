package com.yambrosio.bankingapp.ui.auth.login

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yambrosio.bankingapp.core.NetworkHelper
import com.yambrosio.bankingapp.core.Resource
import com.yambrosio.bankingapp.core.RetrofitHandleCall.handleCallResponse
import com.yambrosio.bankingapp.data.remote.response.LoginResponse
import com.yambrosio.bankingapp.domain.auth.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val app: Application,
    private val loginUseCase: LoginUseCase,
    private val networkHelper: NetworkHelper
) : AndroidViewModel(app) {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userResponse = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading())
    val userResponse: StateFlow<Resource<LoginResponse>> = _userResponse


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)
            if (result != null) {
                Log.i("fernando", "result OK")
            }
            _isLoading.value = false
        }
    }

    private suspend fun safeLoginCall(username: String, password: String) {
        _userResponse.value = Resource.Loading()
        try {
            if (networkHelper.isNetworkConnected()) {
                val response = loginUseCase(username, password)
                _userResponse.value = handleCallResponse(response)
            } else {
                _userResponse.value = Resource.Error("No internet connection")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _userResponse.value = Resource.Error("Network failure")
                else -> _userResponse.value = Resource.Error(t.message.toString())
            }
        }
    }

    fun login(username: String, password: String) = viewModelScope.launch {
        safeLoginCall(username, password)
    }
}