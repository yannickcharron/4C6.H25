package ca.qc.cstj.bottomnavigation.ui.screens.profile

data class ProfileScreenUiState(
    private val _progression: Float = 0f,
    val isCompleted: Boolean = false
) {
    val progression = _progression.toInt()
}