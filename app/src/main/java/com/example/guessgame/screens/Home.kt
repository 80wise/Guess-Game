package com.example.guessgame.screens

import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guessgame.GradientButton
import com.example.guessgame.Routes
import com.example.guessgame.ui.theme.GuessGameTheme
import com.example.guessgame.ui.theme.Teal200
import com.example.guessgame.ui.theme.red

@Composable
fun Home(navController: NavController) {
    var level: Int = 1
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("WELCOME TO THE GAME", color = Color.Red, fontFamily = FontFamily.SansSerif)
        Spacer(modifier = Modifier.height(120.dp))
        Text(
            "Choose your level \n1.Easy(by default) 1...10\n2.Middle 1..100\n3.Difficult 1...1000",
            color = Teal200
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row {
            GradientButton(
                text = "Easy",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(Teal200, Teal200)
                )
            ) { level = 1 }
            GradientButton(
                text = "Middle",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(Teal200, red)
                )
            ) { level = 2 }
            GradientButton(
                text = "Difficult",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(red, red)
                )
            ) { level = 3 }
        }
        Spacer(modifier = Modifier.height(20.dp))
        GradientButton(
            text = "Start",
            textColor = Color.White,
            gradient = Brush.horizontalGradient(
                colors = listOf(Teal200, red)
            )
        ) {
            navController.navigate(route = Routes.PlayingScreen.route + "/$level")
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        border = BorderStroke(2.dp, Color.White),
        onClick = { onClick() })
    {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = textColor)
        }
    }
}