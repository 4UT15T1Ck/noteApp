package com.example.noteapp.note.domain.use_case

import com.example.noteapp.note.domain.repository.NoteRepository
import org.koin.core.annotation.Factory

@Factory
class ToggleNoteExpansionUseCase (
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.toggleNoteExpansion(id)
    }
}