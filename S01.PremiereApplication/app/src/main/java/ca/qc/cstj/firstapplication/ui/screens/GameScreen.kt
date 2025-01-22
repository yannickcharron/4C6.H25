package ca.qc.cstj.firstapplication.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val MIN_TRY = 1
const val MAX_TRY = 100
const val DEFAULT_TRY = 50

@Composable
fun GameScreen() {

    val numberToGuess by remember { mutableIntStateOf((MIN_TRY..MAX_TRY).random()) }
    var message by remember { mutableStateOf("") }
    var userTry by remember { mutableIntStateOf(DEFAULT_TRY) }
    Log.d("[GameScreen]",numberToGuess.toString())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = userTry.toString(), fontSize = 32.sp)
            Column {
                Button(onClick = {
                    if(userTry < MAX_TRY ) {
                        userTry++
                    }
                }) {
                    Text(text = "+", fontSize = 20.sp)
                }
                Button(onClick = {
                    if(userTry > MIN_TRY) {
                        userTry--
                    }

                }) {
                    Text(text = "-", fontSize = 20.sp)
                }
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                message = if(userTry == numberToGuess) {
                    "You won!"
                } else if(userTry < numberToGuess) {
                    "Too low"
                } else {
                    "Too high"
                }
            }
        ) {
            Text(text = "Try", fontSize = 32.sp)
        }
        Text(text = message, fontSize = 32.sp)
    }
}