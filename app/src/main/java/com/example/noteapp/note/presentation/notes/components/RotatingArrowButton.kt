package com.example.noteapp.note.presentation.notes.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun RotatingArrowButton(
    onClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "rotationAnimation"
    )

    IconButton(
        onClick = {
            onClick()
            isExpanded = !isExpanded
        }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Expand or Collapse",
            modifier = Modifier.rotate(rotationAngle)
        )
    }
}

