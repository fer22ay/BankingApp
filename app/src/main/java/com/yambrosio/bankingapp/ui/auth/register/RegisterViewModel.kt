package com.yambrosio.bankingapp.ui.auth.register

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yambrosio.bankingapp.domain.auth.register.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val app: Application,
    private val addUserUseCase: AddUserUseCase
) :
    AndroidViewModel(app) {

    private val _typeEmployee = MutableLiveData<Long>()
    val typeEmployee: LiveData<Long> = _typeEmployee

    private val _company = MutableLiveData<Long>()
    val company: LiveData<Long> = _company

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isButtonEnable = MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    private val _isDialogShown = mutableStateOf(false)
    val isDialogShown: State<Boolean> = _isDialogShown

    fun onRegisterChanged(
        type: Long, company: Long, name: String, username: String,
        password: String, email: String
    ) {
        _typeEmployee.value = type
        _company.value = company
        _name.value = name
        _username.value = username
        _password.value = password
        _email.value = email
        enableLogin(type, company, name, username, password, email)
    }

    private fun enableLogin(
        type: Long,
        company: Long,
        name: String,
        username: String,
        password: String,
        email: String
    ) =
        type > 0 && company > 0 && name.isNotBlank() && username.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    private fun doRegister(
        type: Long,
        company: Long,
        name: String,
        username: String,
        password: String,
        email: String?
    ) {

    }

    fun onConfirmButtonDialog() {
        _isDialogShown.value = false
    }

    fun onDismissDialog() {
        _isDialogShown.value = false
    }

    fun onLoginSelected() {
        viewModelScope.launch {
            doRegister(
                _typeEmployee.value!!,
                _company.value!!,
                _name.value!!,
                _username.value!!,
                _password.value!!,
                _email.value!!
            )
            _isDialogShown.value = true
        }
    }

}