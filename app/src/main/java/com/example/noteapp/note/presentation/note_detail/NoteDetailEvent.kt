package com.example.noteapp.note.presentation.note_detail

import androidx.compose.ui.focus.FocusState

sealed class NoteDetailEvent {
    data class EnterTitle(val value: String): NoteDetailEvent()
    data class ChangeTitleFocus(val focusState: FocusState): NoteDetailEvent()
    data class EnterDescription(val value: String): NoteDetailEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): NoteDetailEvent()
    data class ChangeColor(val color: Int): NoteDetailEvent()
    data object SaveNote: NoteDetailEvent()
}