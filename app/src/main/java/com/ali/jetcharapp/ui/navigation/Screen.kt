package com.ali.jetcharapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailCharacter : Screen("home/{characterId}"){
        fun createRoute(characterId:Long) = "home/$characterId"
    }
}