package ca.qc.cstj.composables.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ca.qc.cstj.composables.data.MockData
import ca.qc.cstj.composables.ui.theme.Blue1
import ca.qc.cstj.composables.ui.theme.Blue2
import ca.qc.cstj.composables.ui.theme.Blue3
import ca.qc.cstj.composables.ui.theme.Green1
import ca.qc.cstj.composables.ui.theme.Green2
import ca.qc.cstj.composables.ui.theme.Green3
import ca.qc.cstj.composables.ui.theme.Orange1
import ca.qc.cstj.composables.ui.theme.Orange2
import ca.qc.cstj.composables.ui.theme.Orange3
import ca.qc.cstj.composables.ui.theme.Pink1
import ca.qc.cstj.composables.ui.theme.Pink2
import ca.qc.cstj.composables.ui.theme.Pink3
import kotlin.random.Random

data class Meditation(
    val title: String,
    val duration: Int = (1 .. 10).random(),
    private val _tags: List<String> = MockData.meditationTags.shuffled().take(Random.nextInt(2,5)),
    private val _icon: String = MockData.meditationIcons.random(),
    private val _color: String = MockData.meditationColors.random()
) {
    val colors: Triple<Color, Color, Color>
        get() = when(_color) {
            "blue" -> Triple(Blue3, Blue2, Blue1)
            "pink" -> Triple(Pink1, Pink2, Pink3)
            "green" -> Triple(Green3, Green2, Green1)
            "orange" -> Triple(Orange3, Orange2, Orange1)
            else -> Triple(Blue3, Blue2, Blue1)
        }

    val icon : ImageVector
        get() = when(_icon) {
            "music" -> Icons.Default.MusicNote
            "night" -> Icons.Default.NightsStay
            "headphone" -> Icons.Default.Headphones
            "meditation" -> Icons.Default.SelfImprovement
            else -> Icons.Default.Headphones
        }
}
