package com.yambrosio.bankingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yambrosio.bankingapp.ui.auth.login.components.LoginScreen
import com.yambrosio.bankingapp.ui.auth.register.components.RegisterScreen
import com.yambrosio.bankingapp.ui.home.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(onHome = { navController.navigate(Screen.Login.route) })
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLogin = { navController.navigate(Screen.Register.route) },
                onHome = { navController.navigate(Screen.Home.route) })
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(onLoginBack = { navController.navigate(Screen.Login.route) })
        }
    }
}