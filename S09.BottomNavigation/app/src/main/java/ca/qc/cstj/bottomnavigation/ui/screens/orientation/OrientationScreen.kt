package ca.qc.cstj.bottomnavigation.ui.screens.orientation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.qc.cstj.bottomnavigation.R

@Composable
fun OrientationScreen() {
    //TODO:
}

@Composable
private fun LandscapeMode() {
    //TODO:
}


@Composable
private fun PortraitMode() {
    //TODO:
}

@Composable
fun SoundSection() {
    //https://pixabay.com/sound-effects/
    //TODO:

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            IconButton(onClick = {
                //TODO:
            }) {
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }

            IconButton(onClick = {
                //TODO:
            }) {
                Icon(
                    imageVector = Icons.Default.PauseCircle,
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }
        }
    }
}

@Composable
fun IntentsSection() {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Button(onClick = {
                //TODO:
            }) {
                Text(text = stringResource(R.string.phone))
            }
        }

        item {
            Button(onClick = {
                //TODO:
            }) {
                Text(text = stringResource(R.string.sms))
            }
        }

        item {
            Button(onClick = {
                //TODO:
            }) {
                Text(text = stringResource(R.string.maps))
            }
        }
    }
}