package com.example.noteapp.note.domain.use_case

import com.example.noteapp.note.domain.model.Note
import com.example.noteapp.note.domain.repository.NoteRepository
import org.koin.core.annotation.Factory

@Factory
class DeleteNoteUseCase (
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}