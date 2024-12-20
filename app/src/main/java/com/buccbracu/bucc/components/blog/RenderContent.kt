package com.buccbracu.bucc.components.blog

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
fun RenderContent(
    content: List<Content>
) {
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
                println("Image Found ${item.attrs.toString()}")
                item.attrs?.let { attrs ->
                    attrs.src?.let {
                        ImageContent(attrs.src)
                    }
                }
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

            "orderedList" -> {

                    OrderedListContent(item)
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




