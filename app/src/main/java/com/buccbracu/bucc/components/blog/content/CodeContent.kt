package com.buccbracu.bucc.components.blog.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.backend.remote.models.Content

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CodeContent(content: Content, fontSize: TextUnit = 14.sp) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2E3440), RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(
            text = content.content?.joinToString(separator = "\n") { it.text.orEmpty() } ?: "",
            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF88C0D0)),
            fontFamily = FontFamily.Monospace,
            fontSize = fontSize
        )
    }
}
