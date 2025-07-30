package com.example.noteapp.note.presentation.util

sealed class Screen (val route: String) {
    data object NoteScreen: Screen("note_screen")
    data object NoteDetailScreen: Screen("note_detail_screen")
}