package com.yambrosio.bankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.yambrosio.bankingapp.ui.auth.login.LoginViewModel
import com.yambrosio.bankingapp.ui.auth.register.RegisterViewModel
import com.yambrosio.bankingapp.ui.navigation.SetupNavGraph
import com.yambrosio.bankingapp.ui.theme.BankingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankingAppTheme {
                ChangeSystemBarsTheme(lightTheme = !isSystemInDarkTheme())
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content(loginViewModel = loginViewModel, registerViewModel = registerViewModel)
                }
            }
        }
    }

    @Composable
    fun Content(loginViewModel: LoginViewModel, registerViewModel: RegisterViewModel) {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController, loginViewModel = loginViewModel, registerViewModel = registerViewModel)
    }

    @Composable
    private fun ChangeSystemBarsTheme(lightTheme: Boolean) {
        val barColor = MaterialTheme.colorScheme.background.toArgb()
        LaunchedEffect(barColor) {
            if (lightTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        barColor, barColor
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        barColor, barColor
                    )
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        barColor
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        barColor
                    )
                )
            }
        }
    }
}