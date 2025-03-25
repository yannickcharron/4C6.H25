package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import ca.qc.cstj.remotedatasource.model.Planet

sealed interface PlanetDetailsScreenUiState {
    data class Success(val planet: Planet) : PlanetDetailsScreenUiState
    data object Loading : PlanetDetailsScreenUiState
    data class Error(val exception: Exception, val message: String) : PlanetDetailsScreenUiState
}