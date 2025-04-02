package ca.qc.cstj.bottomnavigation.ui.screens.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ca.qc.cstj.bottomnavigation.R
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
            Text(text =
                if(titleFormatArgs.isNotEmpty()) {
                    //Ici le title doit avoir des %s, %.2f, %d
                    stringResource(currentDestination.title(), *titleFormatArgs)
                } else {
                    stringResource(currentDestination.title())
                },
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        navigationIcon = {
            if(currentDestination.isNavigateUpVisible()) {
                IconButton(onClick = {
                    onNavigateUp()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.navigate_up))
                }
            }
        }
    )
}