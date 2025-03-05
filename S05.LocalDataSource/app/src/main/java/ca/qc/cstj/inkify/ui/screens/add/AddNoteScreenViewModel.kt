package ca.qc.cstj.inkify.ui.screens.add

import android.app.Application
import android.view.WindowInsets.Side
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.inkify.data.database.AppDatabase
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddNoteScreenViewModel(application: Application)
    : AndroidViewModel(application)
{

    private val _uiState = MutableStateFlow(AddNoteScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow = _sideEffectChannel.receiveAsFlow()

    private val noteRepository = AppDatabase.instance(application).noteRepository()

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

    fun save() {
        viewModelScope.launch {
            try {
                val note = _uiState.value.newNote
                noteRepository.create(note)
                _sideEffectChannel.send(SideEffect.NoteSaved(note))
            } catch(_: Exception) {
                _sideEffectChannel.send(SideEffect.NoteError)
            }
        }
    }

    sealed interface SideEffect {
        data class NoteSaved(val note: Note): SideEffect
        data object NoteError : SideEffect
    }

}