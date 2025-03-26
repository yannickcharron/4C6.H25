package ca.qc.cstj.bottomnavigation.ui.screens.planets.details

import ca.qc.cstj.bottomnavigation.model.Planet

sealed class PlanetDetailsScreenUiState {
    data class Success(val planet: Planet) : PlanetDetailsScreenUiState()
    data object Loading : PlanetDetailsScreenUiState()
    data class Error(val exception : Exception) : PlanetDetailsScreenUiState()

}