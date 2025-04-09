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
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination
import ca.qc.cstj.bottomnavigation.ui.screens.main.MainViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    sendMainScreenSideEffect : (MainViewModel.ChildrenSideEffect) -> Unit = {},
    toMapScreen : () -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(true) {
        //TODO: UpdateBadges
        sendMainScreenSideEffect(MainViewModel.ChildrenSideEffect.UpdateBadges(
            mapOf(Destination.PlanetList to "5")
            //monDictionnaire[Destination.PlanetList] = 5
        ))
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        Button(
            onClick = {
                sendMainScreenSideEffect(MainViewModel.ChildrenSideEffect.UpdateBadges(
                    mapOf(Destination.Barcode to "New")
                    //monDictionnaire[Destination.PlanetList] = 5
                ))
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
                toMapScreen()
            }
        ) {
            Text(text = stringResource(R.string.map))
        }

        //TODO: TIMER

    }

}