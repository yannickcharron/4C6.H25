package ca.qc.cstj.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.qc.cstj.composables.ui.components.TopBar
import ca.qc.cstj.composables.ui.screens.meditation.MeditationScreen
import ca.qc.cstj.composables.ui.theme.ComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposablesTheme(dynamicColor = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar("Yannick") },
                    bottomBar = { }
                ) { innerPadding ->
                    MeditationScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

