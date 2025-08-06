package com.example.noteapp.note.data.repository

import com.example.noteapp.note.data.source.NoteDAO
import com.example.noteapp.note.domain.model.Note
import com.example.noteapp.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImplement (
    private val dao: NoteDAO
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }

    override suspend fun toggleNoteExpansion(id: Int) {
        return dao.toggleNoteExpansion(id)
    }
}