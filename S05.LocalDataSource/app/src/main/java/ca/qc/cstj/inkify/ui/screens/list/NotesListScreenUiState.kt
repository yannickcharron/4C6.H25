package ca.qc.cstj.inkify.ui.screens.list

import ca.qc.cstj.inkify.models.Note

data class NotesListScreenUiState(
    val notes: List<Note> = listOf()
)
