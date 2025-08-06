package com.example.noteapp.note.presentation.notes

import com.example.noteapp.note.domain.model.Note
import com.example.noteapp.note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    data class ToggleNoteExpansion(val id: Int): NotesEvent()
    data object RestoreNote: NotesEvent()
    data object ToggleOrderSection: NotesEvent()
}