package com.example.noteapp.note.presentation.notes

import com.example.noteapp.note.domain.model.Note
import com.example.noteapp.note.domain.util.NoteOrder
import com.example.noteapp.note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
