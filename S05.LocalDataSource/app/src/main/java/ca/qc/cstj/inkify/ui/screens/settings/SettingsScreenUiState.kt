package ca.qc.cstj.inkify.ui.screens.settings

import ca.qc.cstj.inkify.models.InkifySettings

data class SettingsScreenUiState(
    val settings: InkifySettings = InkifySettings()
)