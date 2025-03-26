package ca.qc.cstj.bottomnavigation.ui.screens.planets.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.bottomnavigation.core.composables.ErrorMessage
import ca.qc.cstj.bottomnavigation.core.composables.LoadingAnimation
import ca.qc.cstj.bottomnavigation.ui.components.PlanetCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlanetListScreen(
    viewModel: PlanetsListViewModel = viewModel(),
    toPlanetDetailScreen: (String) -> Unit
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                viewModel.refreshPlanet()
            }
        }
    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState) {
            is PlanetsListScreenUiState.Error -> {
                ErrorMessage(ex = uiState.exception)
            }
            PlanetsListScreenUiState.Loading -> {
                LoadingAnimation()
            }
            is PlanetsListScreenUiState.Success -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    LazyColumn {
                        items(uiState.planets) {
                            PlanetCard(planet = it, onClick = { planet ->
                                toPlanetDetailScreen(planet.href)
                            })
                        }
                    }
                }
            }
        }
    }

}