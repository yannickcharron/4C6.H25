package ca.qc.cstj.composables.ui.screens.title

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TitleScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TitleScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun updateName(newName: String) {
        _uiState.update {
            _uiState.value.copy(
                name = newName
            )
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update {
            _uiState.value.copy(
                password = newPassword
            )
        }
    }
}