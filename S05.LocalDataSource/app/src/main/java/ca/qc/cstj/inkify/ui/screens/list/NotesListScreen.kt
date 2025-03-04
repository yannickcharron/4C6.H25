package ca.qc.cstj.inkify.ui.screens.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.qc.cstj.inkify.R
import ca.qc.cstj.inkify.ui.components.NoteCard
import ca.qc.cstj.inkify.ui.components.TopBar

@Composable
fun NotesListScreen(
    viewModel: NotesListScreenViewModel = viewModel(),
    toAddNoteScreen: () -> Unit,
    toSettingsScreen: () -> Unit,
    navigateUp: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = stringResource(R.string.app_name),
                navigateUp = { navigateUp() },
                toSettingScreen = { toSettingsScreen() })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    toAddNoteScreen()
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = Icons.Filled.Add.name)
            }
        }
    ) { innerPaddings ->
        LazyColumn(modifier = Modifier.padding(innerPaddings)) {
            items(uiState.notes) { note ->
               NoteCard(
                   note = note,
                   onDeleteNote = { noteToDelete -> viewModel.delete(noteToDelete)}
               )
            }
        }
    }
}