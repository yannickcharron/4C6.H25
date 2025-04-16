package ca.qc.cstj.bottomnavigation.ui.screens.orientation

import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.LocalLifecycleOwner
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.composables.YoutubePlayer

@Composable
fun OrientationScreen() {
    val orientation = LocalConfiguration.current.orientation
    if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeMode()
    } else {
        PortraitMode()
    }
}

@Composable
private fun LandscapeMode() {
    Row(
        modifier = Modifier.fillMaxSize().padding(4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(0.5f)) {
            YoutubePlayer(youtubeVideoId = "dQw4w9WgXcQ", lifecycleOwner = LocalLifecycleOwner.current)
            SoundSection()
        }
        IntentsSection()
    }
}


@Composable
private fun PortraitMode() {
    Column(
        modifier = Modifier.fillMaxSize().padding(4.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        YoutubePlayer(youtubeVideoId = "dQw4w9WgXcQ", lifecycleOwner = LocalLifecycleOwner.current)
        SoundSection()
        IntentsSection()
    }
}

@Composable
fun SoundSection() {
    //https://pixabay.com/sound-effects/
    val mediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.chonologyoflove)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            IconButton(onClick = {
                mediaPlayer.start()
            }) {
                Icon(
                    imageVector = Icons.Default.PlayCircle,
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }

            IconButton(onClick = {
                mediaPlayer.pause()
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
                val dialIntent = Intent(Intent.ACTION_DIAL, "tel:450-436-1580".toUri())
                context.startActivity(dialIntent)
            }) {
                Text(text = stringResource(R.string.phone))
            }
        }

        item {
            Button(onClick = {
                val smsIntent = Intent(Intent.ACTION_VIEW, "smsto:4504361580".toUri())
                smsIntent.putExtra("sms_body", "Bonjour de mon application Android")
                context.startActivity(smsIntent)
            }) {
                Text(text = stringResource(R.string.sms))
            }
        }

        item {
            Button(onClick = {
                val gmmIntentUri = "geo:0,0?q=restaurants".toUri()
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }) {
                Text(text = stringResource(R.string.maps))
            }
        }
    }
}