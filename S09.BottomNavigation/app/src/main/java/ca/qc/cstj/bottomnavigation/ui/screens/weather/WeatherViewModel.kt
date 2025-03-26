package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.lifecycle.ViewModel
import ca.qc.cstj.bottomnavigation.data.repositories.CurrentWeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val currentWeatherRepository = CurrentWeatherRepository()

    fun updateSearchTextState(newValue: String) {
        //TODO:
    }

    fun search() {
       //TODO:
    }
}