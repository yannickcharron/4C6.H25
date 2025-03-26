package ca.qc.cstj.bottomnavigation.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(true) {
        //TODO: UpdateBadges
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        Button(
            onClick = {
                //TODO: UpdateBadges
            }
        ) {
            Text(text = stringResource(R.string.add_notification))
        }
        Button(
            onClick = {
                //TODO: Snackbar
            }
        ) {
            Text(text = stringResource(R.string.snackbar))
        }

        Button(
            onClick = {
                //TODO: TO MapScreen
            }
        ) {
            Text(text = stringResource(R.string.map))
        }

        //TODO: TIMER

    }

}