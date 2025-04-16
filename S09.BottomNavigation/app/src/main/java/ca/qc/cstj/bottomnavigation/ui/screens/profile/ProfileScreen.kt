package ca.qc.cstj.bottomnavigation.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination
import ca.qc.cstj.bottomnavigation.ui.screens.main.MainViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    sendMainScreenSideEffect : (MainViewModel.ChildrenSideEffect) -> Unit = {},
    toMapScreen : (Double, Double) -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val currentContext  = LocalContext.current

    LaunchedEffect(true) {
        //TODO: UpdateBadges
        sendMainScreenSideEffect(MainViewModel.ChildrenSideEffect.UpdateBadges(
            mapOf(Destination.PlanetList to "5")
            //monDictionnaire[Destination.PlanetList] = 5
        ))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
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
                val message = currentContext.getString(R.string.message_mettre_dans_le_snackbar)
                sendMainScreenSideEffect(MainViewModel.ChildrenSideEffect
                    .ShowSnackbar(message, "Fermer"))
            }
        ) {
            Text(text = stringResource(R.string.snackbar))
        }

        Button(
            onClick = {
                toMapScreen(45.783333, -74.000000)
            }
        ) {
            Text(text = stringResource(R.string.map))
        }

        if(uiState.isCompleted) {
            Text(text = stringResource(R.string.fin_du_timer), color = Color.Magenta, fontSize = 32.sp)
        } else {
            Text(text = uiState.progression.toString(), color = Color.Green, fontSize = 32.sp)
        }

    }

}