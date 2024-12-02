package com.buccbracu.bucc.components.blogs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.backend.remote.models.Content

@Composable
fun RenderContent(content: Content) {
    when (content.type) {
        "paragraph" -> {
            // Paragraph content
            content.content?.forEach { contentItem ->
                if (contentItem.type == "text") {
                    Text(
                        text = contentItem.text.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        "heading" -> {
            // Heading content
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
                        style = textStyle
                    )
                }
            }
        }

        "hardBreak" -> {
            // Hard break (spacer)
            Spacer(modifier = Modifier.height(8.dp))
        }

        else -> {
            // Handle unknown types
            Text(
                text = "Unsupported content type: ${content.type}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red
            )
        }
    }
}