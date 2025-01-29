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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.firstapplication.R
import ca.qc.cstj.firstapplication.core.Constants
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit


@Composable
fun GameScreen(viewModel: GameScreenViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    if(uiState.status == GameStatus.WIN) {
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(
                Party(
                    speed = 0f,
                    maxSpeed = 30f,
                    damping = 0.9f,
                    spread = 360,
                    colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                    emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
                    position = Position.Relative(0.5, 0.3)
                )
            )
        )
    }

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
            Text(text = uiState.userTry.toString(), fontSize = 32.sp)
            Column {
                Button(onClick = {
                    viewModel.increment()
                }) {
                    Text(text = "+", fontSize = 20.sp)
                }
                Button(onClick = {
                    viewModel.decrement()
                }) {
                    Text(text = "-", fontSize = 20.sp)
                }
            }
        }

        if (uiState.status == GameStatus.WIN) {
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = {
                    viewModel.restart()
                }
            ) {
                Text(text = stringResource(R.string.new_game), fontSize = 28.sp)
            }
        } else {
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = {
                    viewModel.validate()
                }
            ) {
                Text(text = stringResource(R.string.msg_try), fontSize = 28.sp)
            }
        }
        Text(text = when(uiState.status) {
            GameStatus.NEW_GAME -> stringResource(R.string.good_luck)
            GameStatus.TOO_HIGH -> stringResource(R.string.too_high)
            GameStatus.TOO_LOW -> stringResource(R.string.too_low)
            GameStatus.WIN -> stringResource(R.string.you_won)
        }, fontSize = 32.sp)
    }
}