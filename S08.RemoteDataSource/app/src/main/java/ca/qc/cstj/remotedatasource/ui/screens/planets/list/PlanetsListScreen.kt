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
import ca.qc.cstj.remotedatasource.ui.components.PlanetCard

@Composable
fun PlanetListScreen(
    viewModel: PlanetsListViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if(uiState.isError) {
            ErrorMessage(uiState.exception!!)
        }
        else if(uiState.isLoading) {
            LoadingAnimation()
        } else {
            LazyColumn {
                items(uiState.planets) {
                    PlanetCard(planet = it)
                }
            }
        }

    }

}