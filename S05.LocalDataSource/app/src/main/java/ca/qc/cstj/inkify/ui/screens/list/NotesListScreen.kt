package ca.qc.cstj.inkify.ui.screens.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NotesListScreen(
    viewModel: NotesListScreenViewModel = viewModel(),
    toAddNoteScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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

        }
    }
}