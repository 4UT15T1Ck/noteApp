package com.example.noteapp.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.LightBlue
import com.example.noteapp.ui.theme.LightGreen
import com.example.noteapp.ui.theme.LightIndigo
import com.example.noteapp.ui.theme.LightOrange
import com.example.noteapp.ui.theme.LightRed
import com.example.noteapp.ui.theme.LightViolet
import com.example.noteapp.ui.theme.LightYellow

@Entity
data class Note(
    val title: String,
    val description: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,
) {
    companion object {
        val noteColor = listOf(LightBlue, LightGreen, LightIndigo, LightRed, LightYellow, LightOrange, LightViolet)
    }
}

class InvalidNoteException(message: String): Exception(message)
