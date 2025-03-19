package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import ca.qc.cstj.remotedatasource.model.Planet

data class PlanetsListScreenUiState(
    val planets: List<Planet> = listOf(),
    val isError: Boolean = false,
    val exception: Exception? = null,
    val isLoading: Boolean = false
)