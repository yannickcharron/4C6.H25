package ca.qc.cstj.bottomnavigation.ui.screens.main

import androidx.lifecycle.ViewModel
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow = _sideEffectChannel.receiveAsFlow()

    fun onSideEffect(sideEffect: ChildrenSideEffect) {
        when(sideEffect) {
            is ChildrenSideEffect.FormatTitle -> {
                _uiState.update {
                    it.copy(titleArgs = sideEffect.titleArgs)
                }
            }
            is ChildrenSideEffect.UpdateBadges -> {
                _uiState.update {
                    it.copy(bottomBarBadges = sideEffect.badges)
                }
            }
        }
    }

    sealed interface ChildrenSideEffect {
        data class FormatTitle(val titleArgs: List<Any>):ChildrenSideEffect
        data class UpdateBadges(val badges: Map<Destination, String> ):ChildrenSideEffect
    }

    sealed interface SideEffect {
        //TODO:
    }

}