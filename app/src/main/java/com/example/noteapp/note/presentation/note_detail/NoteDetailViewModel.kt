package com.example.noteapp.note.presentation.note_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.note.domain.model.InvalidNoteException
import com.example.noteapp.note.domain.model.Note
import com.example.noteapp.note.domain.use_case.NoteUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class NoteDetailViewModel(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Give your note a title.."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteDescription = mutableStateOf(
        NoteTextFieldState(
            hint = "Add description for more detail.."
        )
    )
    val noteDescription: State<NoteTextFieldState> = _noteDescription

    private val _noteColor = mutableIntStateOf(Note.noteColor.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteByIdUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = note.title.isEmpty()
                        )
                        _noteDescription.value = noteDescription.value.copy(
                            text = note.description,
                            isHintVisible = note.description.isEmpty()
                        )
                        _noteColor.intValue = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteDetailEvent) {
        when (event) {
            is NoteDetailEvent.EnterTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }

            is NoteDetailEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }

            is NoteDetailEvent.EnterDescription -> {
                _noteDescription.value = noteDescription.value.copy(
                    text = event.value
                )
            }

            is NoteDetailEvent.ChangeDescriptionFocus -> {
                _noteDescription.value = noteDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteDescription.value.text.isBlank()
                )
            }

            is NoteDetailEvent.ChangeColor -> {
                _noteColor.intValue = event.color
            }

            is NoteDetailEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                title = noteTitle.value.text,
                                description = noteDescription.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Can not save note!!!"
                            )
                        )
                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object SaveNote : UiEvent()
    }
}