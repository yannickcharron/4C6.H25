package ca.qc.cstj.bottomnavigation.ui.screens.planets.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.core.composables.ErrorMessage
import ca.qc.cstj.bottomnavigation.core.composables.LoadingAnimation
import ca.qc.cstj.bottomnavigation.ui.components.PlanetCard
import ca.qc.cstj.bottomnavigation.ui.components.PortalCard
import ca.qc.cstj.bottomnavigation.ui.screens.main.MainViewModel

@Composable
fun PlanetDetailsScreen(
    viewModel: PlanetDetailsViewModel = viewModel(),
    sendMainScreenSideEffect : (MainViewModel.ExternalSideEffect) -> Unit = {}
) {

    when(val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is PlanetDetailsScreenUiState.Error -> {
            ErrorMessage(ex = uiState.exception)
        }
        PlanetDetailsScreenUiState.Loading -> {
            LoadingAnimation()
        }
        is PlanetDetailsScreenUiState.Success -> {
            //TODO: Format Top Bar
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PlanetCard(planet = uiState.planet, unit = Constants.TemperatureUnit.Kelvin)
                Text(text = stringResource(R.string.portals), style = MaterialTheme.typography.headlineLarge)
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(uiState.planet.portals) {
                        PortalCard(it)
                    }
                }
            }
        }
    }
}
