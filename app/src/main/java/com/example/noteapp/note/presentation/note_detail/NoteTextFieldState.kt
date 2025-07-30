package com.example.noteapp.note.presentation.note_detail

data class NoteTextFieldState (
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)