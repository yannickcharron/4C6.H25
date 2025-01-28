package ca.qc.cstj.firstapplication.ui.screens

import androidx.lifecycle.ViewModel
import ca.qc.cstj.firstapplication.core.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameScreenViewModel : ViewModel() {

    private var _answer = (Constants.MIN_TRY..Constants.MAX_TRY).random()

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun increment() {
        if(uiState.value.userTry >= Constants.MAX_TRY)
            return

        _uiState.update { oldUiState ->
            _uiState.value.copy(userTry = oldUiState.userTry + 1)
        }

        //_uiState.value = _uiState.value.copy(userTry = _uiState.value.userTry + 1 )
    }

    fun decrement() {
        if(uiState.value.userTry <= Constants.MIN_TRY)
            return

        _uiState.update {
            _uiState.value.copy(userTry = it.userTry - 1)
        }
    }

    fun validate() {

        val newStatus = if(uiState.value.userTry == _answer) {
            GameStatus.WIN
        } else if(uiState.value.userTry <_answer) {
            GameStatus.TOO_LOW
        } else {
            GameStatus.TOO_HIGH
        }

        _uiState.update {
            _uiState.value.copy(status = newStatus)
        }

    }

    fun restart() {
        _answer = (Constants.MIN_TRY..Constants.MAX_TRY).random()

        _uiState.update {
            GameUiState()
        }
    }

}