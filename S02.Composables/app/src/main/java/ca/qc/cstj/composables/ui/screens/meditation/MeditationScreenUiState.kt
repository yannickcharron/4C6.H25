package ca.qc.cstj.composables.ui.screens.meditation

import ca.qc.cstj.composables.data.MockData
import ca.qc.cstj.composables.models.Meditation

data class MeditationScreenUiState(
    val searchValue : String = "Yannick",
    val currentMeditation : Meditation = MockData.meditations.random()
)
