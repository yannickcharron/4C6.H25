package ca.qc.cstj.inkify.ui.screens.add

import ca.qc.cstj.inkify.models.Note

data class AddNoteScreenUiState(
    val newNote: Note = Note()
)
