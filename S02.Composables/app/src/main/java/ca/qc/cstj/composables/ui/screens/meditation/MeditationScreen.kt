package ca.qc.cstj.composables.ui.screens.meditation

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.qc.cstj.composables.R
import ca.qc.cstj.composables.core.extensions.colorPaths
import ca.qc.cstj.composables.data.MockData
import ca.qc.cstj.composables.models.Meditation
import ca.qc.cstj.composables.ui.theme.ButtonBlue
import ca.qc.cstj.composables.ui.theme.DarkerButtonBlue
import ca.qc.cstj.composables.ui.theme.TextWhite

@Composable
fun MeditationScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp)
    ) {
        SearchSection()
        TagsSection(MockData.meditationTags)
        CurrentMeditation(MockData.meditations.random())
        MeditationGrid(MockData.meditations)
    }
}

@Composable
fun MeditationGrid(meditations: List<Meditation>) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(),
        columns = GridCells.Fixed(2)
    ) {
        items(meditations) {
            MeditationCard(it)
        }
    }
}

@Composable
fun MeditationCard(meditation: Meditation) {
    Text(text = meditation.title)
}

@Composable
fun CurrentMeditation(meditation: Meditation) {
    Card(
        colors = CardDefaults.cardColors(containerColor = meditation.colors.first)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth()
                .drawBehind {
                    val (mediumColoredPath, lightColoredPath) = colorPaths(size.width, size.height)
                    drawPath(path = mediumColoredPath, color = meditation.colors.second)
                    drawPath(path = lightColoredPath, color = meditation.colors.third)
                }
        ) {
            Text(text = meditation.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = stringResource(R.string.minutes, meditation.duration), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun SearchSection() {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .heightIn(min = 56.dp)
            .border(width = 1.dp, color = TextWhite, shape = RoundedCornerShape(8.dp)),
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = TextWhite,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = DarkerButtonBlue
        ),
        placeholder = {
            Text(text = stringResource(R.string.search), fontWeight = FontWeight.SemiBold)
        }
    )
}

@Composable
fun TagsSection(tags: List<String>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags) {
            FilterChip(
                selected = false,
                onClick = {},
                label = {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = DarkerButtonBlue,
                    selectedContainerColor = ButtonBlue
                ),
                border = FilterChipDefaults.filterChipBorder(
                    selected = false,
                    enabled = true,
                    borderColor = DarkerButtonBlue
                )
            )

        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MeditationScreenPreview() {
    MeditationScreen()
}