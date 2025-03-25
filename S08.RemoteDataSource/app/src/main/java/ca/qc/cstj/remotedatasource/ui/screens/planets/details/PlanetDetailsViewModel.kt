package ca.qc.cstj.remotedatasource.ui.screens.planets.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import ca.qc.cstj.remotedatasource.core.data.ApiResult
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import ca.qc.cstj.remotedatasource.ui.navigation.Destination
import ca.qc.cstj.remotedatasource.ui.screens.planets.list.PlanetsListScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val href = savedStateHandle.toRoute<Destination.PlanetDetailsHref>().href

    private val _uiState = MutableStateFlow<PlanetDetailsScreenUiState>(PlanetDetailsScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val planetRepository = PlanetRepository()

    init {
        viewModelScope.launch {
            planetRepository.retrieveOne(href).collect { result ->
                _uiState.update {
                    when(result) {
                        is ApiResult.Error -> PlanetDetailsScreenUiState.Error(result.exception, result.message)
                        ApiResult.Loading -> PlanetDetailsScreenUiState.Loading
                        is ApiResult.Success -> PlanetDetailsScreenUiState.Success(result.data)
                    }
                }
            }
        }
    }

}