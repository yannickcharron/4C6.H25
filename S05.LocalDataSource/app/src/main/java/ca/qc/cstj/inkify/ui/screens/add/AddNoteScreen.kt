package ca.qc.cstj.inkify.ui.screens.add


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.core.Constants

@Composable
fun AddNoteScreen(
    viewModel: AddNoteScreenViewModel = viewModel()
) {

    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
            ) {
                Icon(imageVector = Icons.Filled.Save, contentDescription = Icons.Filled.Save.name)
            }
        }
    ) { innerPaddings ->
        Column(
            modifier = Modifier.padding(innerPaddings),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.new_note),
                style= MaterialTheme.typography.displayMedium
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.newNote.title,
                onValueChange = {
                    viewModel.updateNoteTitle(it)
                },
                label = {
                    Text(text = stringResource(R.string.title))
                }
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(Constants.NOTES_COLORS) { color ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color(color.toColorInt()))
                            .clickable {
                                viewModel.updateNoteColor(color)
                            }
                    ) {
                        if(uiState.newNote.color == color) {
                            Icon(
                                imageVector = Icons.Sharp.CheckCircle,
                                contentDescription = Icons.Sharp.CheckCircle.name,
                                tint = Color.Black,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = uiState.newNote.content,
                onValueChange = {
                    viewModel.updateNoteContent(it)
                },
                label = {
                    Text(text = stringResource(R.string.content))
                }
            )
        }
    }
}