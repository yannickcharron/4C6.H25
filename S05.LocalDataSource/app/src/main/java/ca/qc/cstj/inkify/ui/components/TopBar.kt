package ca.qc.cstj.inkify.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigateUp: () -> Unit = {},
    toSettingScreen: () -> Unit = {}
) {
    TopAppBar(title = {
        Text(text = title)
    }, navigationIcon = {
        if (navigateUp != {}) {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, null
                )
            }
        }
    }, actions = {
        if (toSettingScreen != {}) {
            IconButton(onClick = {
                toSettingScreen()
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings, contentDescription = null
                )
            }
        }
    })
}