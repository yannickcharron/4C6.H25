package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.remotedatasource.core.data.ApiResult
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetsListViewModel : ViewModel() {

    private val _planetRepository = PlanetRepository()

    private val _uiState = MutableStateFlow<PlanetsListScreenUiState>(PlanetsListScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _planetRepository.retrieveAll().collect { apiResult ->
                _uiState.update {
                    when (apiResult) {
                        is ApiResult.Error -> PlanetsListScreenUiState.Error(apiResult.exception, apiResult.message)
                        ApiResult.Loading -> PlanetsListScreenUiState.Loading
                        is ApiResult.Success -> PlanetsListScreenUiState.Success(planets = apiResult.data)
                    }
                }
            }


        }
    }

}