package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import ca.qc.cstj.remotedatasource.model.Planet

sealed interface PlanetsListScreenUiState {
    data class Success(val planets: List<Planet>) : PlanetsListScreenUiState
    data object Loading : PlanetsListScreenUiState
    data class Error(val exception: Exception, val message: String) : PlanetsListScreenUiState
}
