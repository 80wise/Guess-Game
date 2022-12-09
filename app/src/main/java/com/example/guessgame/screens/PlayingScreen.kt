package com.example.guessgame.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.guessgame.ui.theme.Teal200
import com.example.guessgame.ui.theme.red

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayingScreen(level: Int, generatedNumber: Int, attempts: Int) {

    var attempts = attempts

    var providedNumber = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CustomizedText(level = level, attempts = attempts)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = providedNumber.value, color = Teal200)
        Spacer(modifier = Modifier.height(50.dp))
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            content = {
                items(9) { i ->
                    Button(
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                        border = BorderStroke(2.dp, White),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Teal200
                        ),
                        onClick = {
                            if (providedNumber.value.isDigitsOnly()) {
                                providedNumber.value += (i + 1).toString()
                            } else {
                                providedNumber.value = (i + 1).toString()
                            }
                        }
                    ) {
                        Text("${i + 1}", color = White)
                    }
                }
            }
        )
        Row() {
            GradientButton(
                text = "Delete",
                textColor = White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(Teal200, red)
                )
            ) {
                providedNumber.value = providedNumber.value.dropLast(1)
            }
            Button(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                border = BorderStroke(2.dp, White),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Teal200
                ),
                onClick = {
                    if (providedNumber.value.isDigitsOnly()) {
                        providedNumber.value += 0.toString()
                    } else {
                        providedNumber.value = 0.toString()
                    }
                }
            ) {
                Text("0", color = White)
            }
            GradientButton(
                text = "Check",
                textColor = White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(Teal200, red)
                )
            ) {
                attempts--
                if (providedNumber.value == generatedNumber.toString()) {
                    providedNumber.value = "You won :-)"
                } else if (attempts == 0) {
                    providedNumber.value = "You lost, the answer was $generatedNumber :("
                } else {
                    if (providedNumber.value.isDigitsOnly()) {
                        if (providedNumber.value.toInt() > generatedNumber)
                            providedNumber.value =
                                "The provided number is bigger than the generated one"
                        else if (providedNumber.value.toInt() < generatedNumber)
                            providedNumber.value =
                                "The provided number is less than the generated one"
                    }
                }
            }
        }

    }
}

@Composable
fun CustomizedText(level: Int, attempts: Int) {
    var text = "You are playing ";
    when (level) {
        1 -> text += "easy level"
        2 -> text += "middle level"
        3 -> text += "difficult level"
    }

    Text(text = "$text with $attempts attempts", color = Color.Red)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {

}

