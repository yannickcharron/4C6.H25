package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.bottomnavigation.core.data.ApiResult
import ca.qc.cstj.bottomnavigation.data.repositories.CurrentWeatherRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val currentWeatherRepository = CurrentWeatherRepository()

    private var currentWeatherSteveJob: Job? = null

    fun updateSearchTextState(newValue: String) {
        _uiState.update {
            it.copy(searchText = newValue)
        }
    }

    fun search() {
        currentWeatherSteveJob?.cancel()
        currentWeatherSteveJob = viewModelScope.launch {
           currentWeatherRepository.retrieveWithCityName(_uiState.value.searchText).collect { apiResult ->
                _uiState.update {
                    _uiState.value.copy(currentWeatherState =
                        when(apiResult) {
                            is ApiResult.Error ->  CurrentWeatherState.Error(apiResult.exception)
                            ApiResult.Loading -> CurrentWeatherState.Loading
                            is ApiResult.Success -> CurrentWeatherState.Success(apiResult.data)
                        }
                    )
                }
           }
       }
    }
}