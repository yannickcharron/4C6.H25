package ca.qc.cstj.remotedatasource.ui.screens.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetsListViewModel : ViewModel() {

    private val _planetRepository = PlanetRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _planetRepository.retrieveAll()
        }
    }

}