package com.buccbracu.bucc.components.blogs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.backend.remote.models.Content

@Composable
fun HeadingContent(content: Content) {
    val headingLevel = content.attrs?.level ?: 1
    val textStyle = when (headingLevel) {
        1 -> MaterialTheme.typography.headlineLarge
        2 -> MaterialTheme.typography.headlineMedium
        else -> MaterialTheme.typography.headlineSmall
    }

    content.content?.forEach { contentItem ->
        if (contentItem.type == "text") {
            Text(
                text = contentItem.text.orEmpty(),
                style = textStyle,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}