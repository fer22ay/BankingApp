package com.yambrosio.bankingapp.ui.auth.register.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yambrosio.bankingapp.ui.auth.register.RegisterViewModel

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        val isLoading: Boolean by registerViewModel.isLoading.observeAsState(initial = false)
        if (isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            Body(Modifier.align(Alignment.Center), registerViewModel)
        }
    }
}

@Composable
fun Body(modifier: Modifier, registerViewModel: RegisterViewModel) {
    val typeEmployee: Long by registerViewModel.typeEmployee.observeAsState(initial = 0)
    val company: Long by registerViewModel.company.observeAsState(initial = 0)
    val name: String by registerViewModel.name.observeAsState(initial = "")
    val username: String by registerViewModel.username.observeAsState(initial = "")
    val password: String by registerViewModel.password.observeAsState(initial = "")
    val email: String by registerViewModel.email.observeAsState(initial = "")
    val isLoginEnable by registerViewModel.isButtonEnable.observeAsState(initial = false)
    Column(modifier = modifier) {

    }
}