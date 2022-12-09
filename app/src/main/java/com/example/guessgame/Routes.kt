package com.example.guessgame

sealed class Routes(val route: String) {
    object Home : Routes(route = "home")
    object PlayingScreen: Routes(route= "playing_screen")
}
