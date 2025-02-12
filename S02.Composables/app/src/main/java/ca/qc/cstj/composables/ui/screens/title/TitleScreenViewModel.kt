package ca.qc.cstj.composables.ui.screens.title

import android.view.WindowInsets.Side
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TitleScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TitleScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>()
    val sideEffectFlow = _sideEffectChannel.receiveAsFlow()

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

    fun togglePasswordVisibility() {
        _uiState.update {
            _uiState.value.copy(
                isPasswordVisible = !uiState.value.isPasswordVisible
            )
        }
    }

    fun login() {
        if(_uiState.value.password != "123456") {
            _uiState.update {
                _uiState.value.copy(isError = true)
            }
            //launch = nouveau thread/coroutine executant le bloc de code { }
            viewModelScope.launch {
                _sideEffectChannel.send(SideEffect.Disconnected)
            }
        } else {
            _uiState.update {
                _uiState.value.copy(isError = false)
            }
            viewModelScope.launch {
                _sideEffectChannel.send(SideEffect.Connected)
            }
        }
    }

    sealed interface SideEffect {
        data object Connected : SideEffect
        data object Disconnected: SideEffect
    }
}