package ca.qc.cstj.bottomnavigation.ui.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow = _sideEffectChannel.receiveAsFlow()

    fun onSideEffect(sideEffect: ExternalSideEffect) {
        //TODO:
    }

    sealed interface ExternalSideEffect {
        //TODO:
    }

    sealed interface SideEffect {
        //TODO:
    }

}