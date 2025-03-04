package ca.qc.cstj.inkify.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.data.repositories.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(SettingsScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val settingsRepository = SettingsRepository(application)

    init {
        viewModelScope.launch {
//            settingsRepository.settings.collect { settings ->
//                _uiState.update {
//                    _uiState.value.copy(settings = settings)
//                }
//            }

            settingsRepository.jsonSettings.collect { settings ->
                _uiState.update {
                    _uiState.value.copy(settings = settings)
                }
            }
        }
    }


    fun updateName(newName: String) {
        _uiState.update {
            _uiState.value.copy(
                settings = _uiState.value.settings.copy(name = newName)
            )
        }

        save()
    }

    fun updateColor(newColor:String) {
        _uiState.update {
            _uiState.value.copy(
                settings = _uiState.value.settings.copy(noteDefaultColor = newColor)
            )
        }

        save()
    }

    private fun save() {
        try {
            viewModelScope.launch {
                settingsRepository.save(_uiState.value.settings)
            }
        } catch(ex : Exception) {
            //TODO:
        }
    }

    fun reset() {
        viewModelScope.launch {
            try {
                settingsRepository.reset()
            } catch(ex: Exception) {
                //TODO:
            }
        }
    }

}