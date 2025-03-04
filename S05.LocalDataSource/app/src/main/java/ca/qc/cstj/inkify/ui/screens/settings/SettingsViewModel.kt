package ca.qc.cstj.inkify.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(SettingsScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun updateName(newName: String) {
        _uiState.update {
            _uiState.value.copy(
                settings = _uiState.value.settings.copy(name = newName)
            )
        }
    }

    fun updateColor(newColor:String) {
        _uiState.update {
            _uiState.value.copy(
                settings = _uiState.value.settings.copy(noteDefaultColor = newColor)
            )
        }
    }

}