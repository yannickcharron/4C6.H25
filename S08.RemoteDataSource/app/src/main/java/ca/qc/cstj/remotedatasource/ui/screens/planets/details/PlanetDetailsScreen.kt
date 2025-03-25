package ca.qc.cstj.remotedatasource.ui.screens.planets.details

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
import ca.qc.cstj.remotedatasource.R
import ca.qc.cstj.remotedatasource.core.composables.ErrorMessage
import ca.qc.cstj.remotedatasource.core.composables.LoadingAnimation
import ca.qc.cstj.remotedatasource.model.Planet
import ca.qc.cstj.remotedatasource.ui.components.PlanetCard
import ca.qc.cstj.remotedatasource.ui.components.PortalCard
import ca.qc.cstj.remotedatasource.ui.screens.planets.list.PlanetsListScreenUiState


@Composable
fun DetailsPlanetScreenWithoutRefresh(planet: Planet) {
    DetailsPlanet(planet)
}

@Composable
fun DetailsPlanetScreen(
    viewModel: PlanetDetailsViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when(uiState) {
        is PlanetDetailsScreenUiState.Error -> {
            ErrorMessage(uiState.exception)
        }
        PlanetDetailsScreenUiState.Loading -> {
            LoadingAnimation()
        }
        is PlanetDetailsScreenUiState.Success -> {
            DetailsPlanet(planet = uiState.planet)
        }
    }

}

@Composable
private fun DetailsPlanet(planet: Planet) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PlanetCard(
            planet = planet
        )
        Text(text = stringResource(R.string.portals), style = MaterialTheme.typography.headlineLarge)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(planet.portals) {
                PortalCard(it)
            }
        }
    }
}