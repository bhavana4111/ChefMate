package com.chefmate.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chefmate.ui.contact_us.ContactUsScreen
import com.chefmate.ui.detail.DetailScreen
import com.chefmate.ui.login.LoginScreen
import com.chefmate.ui.main.MainScreen
import com.chefmate.ui.register.RegisterScreen
import com.chefmate.ui.searchListing.SearchScreen
import com.chefmate.ui.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.ContactUsScreen.route) {
            ContactUsScreen(navController = navController)
        }
        composable(route = Screen.Detail.route) {
            DetailScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }

}