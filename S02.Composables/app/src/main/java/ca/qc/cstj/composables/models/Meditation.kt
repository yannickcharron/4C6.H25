package ca.qc.cstj.composables.models

import ca.qc.cstj.composables.data.MockData
import kotlin.random.Random

data class Meditation(
    private val _title: String,
    private val _duration: Int = (1 .. 10).random(),
    private val _tags: List<String> = MockData.meditationTags.shuffled().take(Random.nextInt(2,5)),
    private val _icon: String = MockData.meditationIcons.random(),
    private val _color: String = MockData.meditationColors.random()
)
