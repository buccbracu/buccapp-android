package com.buccbracu.bucc.components.blogs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.buccbracu.bucc.backend.remote.models.Content
import com.buccbracu.bucc.backend.remote.models.ContentItem
import com.google.common.io.Files.append

@Composable
fun RenderContent(content: List<Content>) {
    content.forEach { item ->
        when (item.type) {
            "paragraph" -> {
                if (!item.content.isNullOrEmpty()) {
                    ParagraphContent(item.content)
                } else {
                    Spacer(modifier = Modifier.height(8.dp)) // For empty paragraphs
                }
            }

            "heading" -> {
                HeadingContent(item)
            }

            "image" -> {

            }

            "hardBreak" -> {
                Spacer(modifier = Modifier.height(8.dp))
            }

            "youtube" -> {
                item.attrs?.let { attrs ->
                    attrs.src?.let {
                        YoutubeContent(it)
                    }

                }
            }

            else -> {
                Text(
                    text = "Unsupported content type: ${item.type}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red
                )
            }
        }
    }
}




