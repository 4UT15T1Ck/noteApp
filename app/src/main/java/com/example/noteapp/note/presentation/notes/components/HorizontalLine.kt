package com.example.noteapp.note.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLine(
    height: Dp = 2.dp
) {
    Box(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onBackground)
    )
}