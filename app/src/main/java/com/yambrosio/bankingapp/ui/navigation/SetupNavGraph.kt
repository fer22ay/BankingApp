package com.yambrosio.bankingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yambrosio.bankingapp.ui.auth.login.LoginScreen
import com.yambrosio.bankingapp.ui.auth.login.LoginViewModel
import com.yambrosio.bankingapp.ui.home.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController, loginViewModel: LoginViewModel) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}