package com.example.noteapp.note.domain.use_case

import com.example.noteapp.note.domain.model.InvalidNoteException
import com.example.noteapp.note.domain.model.Note
import com.example.noteapp.note.domain.repository.NoteRepository
import org.koin.core.annotation.Factory
import kotlin.jvm.Throws

@Factory
class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("title can't be empty!!")
        }
        repository.insertNote(note)
    }
}