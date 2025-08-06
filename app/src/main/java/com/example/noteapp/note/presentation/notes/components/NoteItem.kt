package com.example.noteapp.note.presentation.notes.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.noteapp.note.domain.model.Note
import com.example.noteapp.ui.theme.LazyFoxFontFamily

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick: () -> Unit,
    onTitleClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onTitleClick()
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(Color(note.color))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = LazyFoxFontFamily,
                    color = Color(note.color),
                    maxLines = 1
                )
            }
            IconButton(
                onClick = onDeleteClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete note",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        AnimatedVisibility(
            visible = note.isExpanded,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
        ) {
            Box(
                modifier = modifier
            ) {
                Canvas(modifier = Modifier.matchParentSize()) {
                    val clipPath = Path().apply {
                        lineTo(size.width - cutCornerSize.toPx(), 0f)
                        lineTo(size.width, cutCornerSize.toPx())
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    }

                    clipPath(clipPath) {
                        drawRoundRect(
                            color = Color(note.color),
                            size = size,
                            cornerRadius = CornerRadius(cornerRadius.toPx())
                        )
                        drawRoundRect(
                            color = Color(0x44000000), // Dark, semi-transparent color
                            topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                            size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                            cornerRadius = CornerRadius(cornerRadius.toPx())
                        )
                    }
                }
                val descriptionText = note.description.ifEmpty { "No Description" }
                Text(
                    text = descriptionText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontFamily = LazyFoxFontFamily,
                    maxLines = 10,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(end = 32.dp)
                )
            }
        }
    }
}
