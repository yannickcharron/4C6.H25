package ca.qc.cstj.bottomnavigation.ui.screens.barcode

import ca.qc.cstj.bottomnavigation.model.CheckIn

sealed class BarcodeScreenUiState() {
    class Error(val exception: Exception) : BarcodeScreenUiState()
    class Success(val checkIns: List<CheckIn>) : BarcodeScreenUiState()
    data object Loading : BarcodeScreenUiState()
}