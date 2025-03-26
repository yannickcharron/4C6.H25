package ca.qc.cstj.bottomnavigation.ui.screens.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    currentDestination: Destination,
    titleFormatArgs: Array<Any>,
    onNavigateUp: () -> Unit
) {

    TopAppBar(
        title = {
            //TODO:
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        navigationIcon = {
            //TODO:
        }
    )
}