package ca.qc.cstj.composables.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ca.qc.cstj.composables.ui.components.BottomBar
import ca.qc.cstj.composables.ui.components.TopBar
import ca.qc.cstj.composables.ui.screens.meditation.MeditationScreen

@Composable
fun MainScreen(name : String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(name) },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        MeditationScreen(modifier = Modifier.padding(innerPadding))
    }
}