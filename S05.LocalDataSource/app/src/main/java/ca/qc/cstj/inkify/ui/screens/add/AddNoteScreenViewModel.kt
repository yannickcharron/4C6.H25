package ca.qc.cstj.inkify.ui.screens.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddNoteScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddNoteScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun updateNoteTitle(newTitle:String) {
        _uiState.update {
            val note = _uiState.value.newNote
            _uiState.value.copy(
                newNote = note.copy(title = newTitle)
            )
        }
    }

    fun updateNoteColor(newColor: String) {
        _uiState.update {
            val note = _uiState.value.newNote
            _uiState.value.copy(
                newNote = note.copy(color = newColor)
            )
        }
    }

    fun updateNoteContent(newContent: String) {
        _uiState.update {
            val note = _uiState.value.newNote
            _uiState.value.copy(
                newNote = note.copy(content = newContent)
            )
        }

    }

}