package com.example.noteapp.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.LightBlue
import com.example.noteapp.ui.theme.LightGreen
import com.example.noteapp.ui.theme.LightIndigo
import com.example.noteapp.ui.theme.LightOrange
import com.example.noteapp.ui.theme.LightRed
import com.example.noteapp.ui.theme.LightViolet

@Entity
data class Note(
    val title: String,
    val description: String,
    val timestamp: Long,
    val color: Int,
    val isExpanded: Boolean = false,
    @PrimaryKey val id: Int? = null,
) {
    companion object {
        val noteColor = listOf(LightBlue, LightGreen, LightIndigo, LightRed, LightOrange, LightViolet)
    }
}

class InvalidNoteException(message: String): Exception(message)
