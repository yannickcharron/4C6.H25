package ca.qc.cstj.composables.ui.screens.meditation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MeditationScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MeditationScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSearchValue(newValue: String) {
        _uiState.update {
            // _uiState.value = implicite
            _uiState.value.copy(
                searchValue = newValue
            )
        }
    }

}