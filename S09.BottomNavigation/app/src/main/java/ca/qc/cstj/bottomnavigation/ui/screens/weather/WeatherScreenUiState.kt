package ca.qc.cstj.bottomnavigation.ui.screens.weather

import ca.qc.cstj.bottomnavigation.model.CurrentWeather

sealed interface WeatherScreenUiState {
    data object  Loading: WeatherScreenUiState
    data class Error(val error: Exception) : WeatherScreenUiState
    data class Success(val currentWeather: CurrentWeather) : WeatherScreenUiState
}