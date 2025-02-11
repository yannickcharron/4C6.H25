package ca.qc.cstj.composables.ui.screens.meditation

import ca.qc.cstj.composables.data.MockData
import ca.qc.cstj.composables.models.Meditation

data class MeditationScreenUiState(
    val searchValue : String = "",
    val currentMeditation : Meditation = MockData.meditations.random(),
    val meditations: List<Meditation> = listOf(),
    val selectedTag: String = MockData.meditationTags.random(),
    val tags: List<String> = listOf()
)
