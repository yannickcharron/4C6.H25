package ca.qc.cstj.bottomnavigation.ui.screens.weather

import ca.qc.cstj.bottomnavigation.model.CurrentWeather

data class WeatherScreenUiState(
    val searchText: String = "",
    val currentWeatherState: CurrentWeatherState = CurrentWeatherState.Idle
)

sealed interface CurrentWeatherState {
    data object Loading: CurrentWeatherState
    data class Error(val error: Exception) : CurrentWeatherState
    data class Success(val currentWeather: CurrentWeather) : CurrentWeatherState
    data object Idle : CurrentWeatherState
}