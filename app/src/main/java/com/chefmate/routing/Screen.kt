package com.chefmate.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MainScreen: Screen("main_screen")
    object ContactUsScreen: Screen("contact_us_screen")
    object ReviewScreen: Screen("review_screen")
    object Detail: Screen("detail_screen")
    object SearchScreen: Screen("search_screen")


}