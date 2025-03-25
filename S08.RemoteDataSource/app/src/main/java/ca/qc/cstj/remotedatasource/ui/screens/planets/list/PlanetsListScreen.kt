package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.remotedatasource.core.composables.ErrorMessage
import ca.qc.cstj.remotedatasource.core.composables.LoadingAnimation
import ca.qc.cstj.remotedatasource.model.Planet
import ca.qc.cstj.remotedatasource.ui.components.PlanetCard

@Composable
fun PlanetListScreen(
    viewModel: PlanetsListViewModel = viewModel(),
    navigateToPlanetDetail: (Planet) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when(uiState) {
            is PlanetsListScreenUiState.Error -> {
                ErrorMessage(uiState.exception)
            }
            PlanetsListScreenUiState.Loading -> {
                LoadingAnimation()
            }
            is PlanetsListScreenUiState.Success -> {
                LazyColumn {
                    items(uiState.planets) {
                        PlanetCard(
                            planet = it,
                            onClick = { planet ->
                                navigateToPlanetDetail(planet)
                            }
                        )
                    }
                }
            }
        }


    }

}