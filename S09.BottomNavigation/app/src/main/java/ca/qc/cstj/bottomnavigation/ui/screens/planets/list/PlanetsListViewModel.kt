package ca.qc.cstj.bottomnavigation.ui.screens.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.core.data.ApiResult
import ca.qc.cstj.bottomnavigation.data.repositories.PlanetRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetsListViewModel : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _uiState = MutableStateFlow<PlanetsListScreenUiState>(PlanetsListScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var _unit = Constants.TemperatureUnit.Celsius

    private var refreshJob : Job? = null

    init {
        refreshPlanet()
    }

    fun changeTemperatureUnit(unit: Constants.TemperatureUnit) {
        _unit = unit
        refreshPlanet()
    }

    fun refreshPlanet() {

        refreshJob?.cancel()
        refreshJob = viewModelScope.launch {
            planetRepository.retrieveAllWithRefresh(_unit).collect { result ->
                _uiState.update {
                    when (result) {
                        is ApiResult.Error -> PlanetsListScreenUiState.Error(result.exception)
                        ApiResult.Loading -> PlanetsListScreenUiState.Loading
                        is ApiResult.Success ->  PlanetsListScreenUiState.Success(result.data, _unit)
                    }
                }
            }
        }
    }
}