package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetsListViewModel : ViewModel() {

    private val _planetRepository = PlanetRepository()

    private val _uiState = MutableStateFlow(PlanetsListScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val planets = _planetRepository.retrieveAll()
            _uiState.update {
                _uiState.value.copy(planets = planets)
            }
        }
    }

}