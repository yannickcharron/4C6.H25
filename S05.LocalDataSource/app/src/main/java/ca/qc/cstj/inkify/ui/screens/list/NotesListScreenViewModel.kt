package ca.qc.cstj.inkify.ui.screens.list

import android.app.Application
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.inkify.data.database.AppDatabase
import ca.qc.cstj.inkify.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesListScreenViewModel(application: Application)
    : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(NotesListScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val noteRepository = AppDatabase.instance(application).noteRepository()

    init {
        viewModelScope.launch {
            noteRepository.retrieveAll().collect { notes ->
                _uiState.update {
                    _uiState.value.copy(notes = notes)
                }

            }
        }

        Log.d("TEST", "Yannick")
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.delete(note)
            } catch(_:Exception) {

            }
        }
    }

}