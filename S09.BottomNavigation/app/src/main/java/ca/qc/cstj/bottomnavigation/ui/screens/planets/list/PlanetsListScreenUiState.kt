package ca.qc.cstj.bottomnavigation.ui.screens.planets.list

import ca.qc.cstj.bottomnavigation.model.Planet

sealed class PlanetsListScreenUiState {
    data class Success(
        val planets: List<Planet>
    ) : PlanetsListScreenUiState()

    data object Loading : PlanetsListScreenUiState()
    data class Error(val exception : Exception) : PlanetsListScreenUiState()
}