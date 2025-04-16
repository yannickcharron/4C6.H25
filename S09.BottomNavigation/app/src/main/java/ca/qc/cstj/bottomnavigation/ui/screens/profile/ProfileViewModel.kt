package ca.qc.cstj.bottomnavigation.ui.screens.profile

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val timer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _uiState.update {
                it.copy(_progression = _uiState.value.progression + 1f)
            }
        }

        override fun onFinish() {
            _uiState.update {
                it.copy(isCompleted = true)
            }
            this.start()
        }

    }

    init {
        timer.start()
    }



}