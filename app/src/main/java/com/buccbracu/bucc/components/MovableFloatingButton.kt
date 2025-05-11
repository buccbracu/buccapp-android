package com.buccbracu.bucc.components

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

import kotlin.math.roundToInt

@Composable
fun MovableFloatingActionButton(
    onClick: () -> Unit,
    icon: ImageVector = Icons.Filled.Add
) {
    // Remember the offset to allow dragging
    val offsetX = rememberSaveable { mutableStateOf(0f) }
    val offsetY = rememberSaveable { mutableStateOf(0f) }

    // Box to hold the button
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 20.dp, bottom = 70.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        // Small Floating Action Button with drag functionality
        FloatingActionButton(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
                .align(Alignment.BottomEnd) // Default bottom-right position
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        // Update offset values based on the drag amount
                        change.consume() // Consume the drag event to prevent further propagation
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                    }
                },
            shape = CircleShape
        ) {
            Icon(icon, contentDescription = "Small floating action button.")
        }
    }
}