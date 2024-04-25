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
import com.chefmate.ui.reviewScreen.ReviewScreen
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
        composable(route = Screen.ReviewScreen.route) {
            ReviewScreen(navController = navController)
        }
        composable(route = Screen.Detail.route+"/{name}"+"/{image}"+"/{price}"+"/{detail}") {
            val name = it.arguments?.getString("name")
            val image = it.arguments?.getString("image")
            val price = it.arguments?.getString("price")
            val detail = it.arguments?.getString("detail")
            if(name!=null && image!=null && price!=null && detail!=null) {
                DetailScreen(navController = navController,name,image.toString().toInt(),price,detail)
            }

        }
        composable(route = Screen.SearchScreen.route+"/{search}"+"/{rate}") {
            val search = it.arguments?.getString("search")
            val rate = it.arguments?.getString("rate")
            SearchScreen(navController = navController,search= search,rate=rate)

        }
    }

}