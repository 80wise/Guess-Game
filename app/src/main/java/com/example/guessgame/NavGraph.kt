package com.example.guessgame

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guessgame.screens.Home
import com.example.guessgame.screens.PlayingScreen
import kotlin.random.Random

@Composable
fun SetupNavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ){
        composable(
            route = Routes.Home.route
        ){
            Home(navController = navController)
        }
        composable(
            route = Routes.PlayingScreen.route + "/{level}"
        ){ navBackStack->
            val level = navBackStack.arguments!!.getInt("level")
            var attempts = 3
            val min = 1
            var max = 10
            when (level) {
                2 -> {
                    max = 100
                    attempts = 10
                }
                3 -> {
                    max = 1000
                    attempts = 50
                }
            }
            val generatedNumber = Random.nextInt(min, max)
            PlayingScreen(level = level, generatedNumber = generatedNumber, attempts)
        }
    }
}