package ca.qc.cstj.bottomnavigation.ui.screens.barcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.bottomnavigation.core.data.ApiResult
import ca.qc.cstj.bottomnavigation.data.repositories.CheckInRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BarcodeViewModel : ViewModel() {

    private val checkInRepository = CheckInRepository()

    private val _uiState = MutableStateFlow<BarcodeScreenUiState>(BarcodeScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()


    fun loadCheckIn() {
        viewModelScope.launch {
            checkInRepository.retrieveAll().collect { apiResult ->
                _uiState.update {
                    when(apiResult) {
                        is ApiResult.Error -> BarcodeScreenUiState.Error(apiResult.exception)
                        ApiResult.Loading -> BarcodeScreenUiState.Loading
                        is ApiResult.Success -> BarcodeScreenUiState.Success(apiResult.data)
                    }
                }
            }
        }
    }

    fun addCheckIn(codeValue: String) {
       //TODO:
    }

}