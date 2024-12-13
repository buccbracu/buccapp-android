package com.buccbracu.bucc.components.blog.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.Content

@Composable
fun QuoteContent(content: Content, fontSize: TextUnit = 14.sp) {


    val quoteText = content.content?.joinToString(separator = "\n") { nestedContentItem ->
        nestedContentItem.content?.joinToString(separator = " ") { nestedContent ->
            nestedContent.text.orEmpty()
        }.orEmpty()
    }?.takeIf { it.isNotBlank() } ?: "Quote not found"

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(
            text = quoteText,
            style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = fontSize
        )
    }
}