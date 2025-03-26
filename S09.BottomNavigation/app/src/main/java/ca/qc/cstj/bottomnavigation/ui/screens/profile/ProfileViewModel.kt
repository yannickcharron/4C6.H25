package ca.qc.cstj.bottomnavigation.ui.screens.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()

    //TODO:

}