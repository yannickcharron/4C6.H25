package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.bottomnavigation.core.data.ApiResult
import ca.qc.cstj.bottomnavigation.data.repositories.CurrentWeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherScreenUiState>(WeatherScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val currentWeatherRepository = CurrentWeatherRepository()

    init {
        search()
    }

    fun updateSearchTextState(newValue: String) {
        //TODO:
    }

    private fun search() {
       viewModelScope.launch {
           currentWeatherRepository.retrieveWithCityName("Sydney").collect { apiResult ->
                _uiState.update {
                    when(apiResult) {
                        is ApiResult.Error -> WeatherScreenUiState.Error(apiResult.exception)
                        ApiResult.Loading -> WeatherScreenUiState.Loading
                        is ApiResult.Success -> WeatherScreenUiState.Success(apiResult.data)
                    }
                }
           }
       }
    }
}