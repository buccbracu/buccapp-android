package com.buccbracu.bucc.components.blogs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
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
                item.attrs?.let { attrs ->
//                    Image(
//                        painter = rememberImagePainter(attrs.src),
//                        contentDescription = attrs.alt,
//                        modifier = Modifier.fillMaxWidth(),
//                        contentScale = ContentScale.Crop
//                    )
                }
            }

            "hardBreak" -> {
                Spacer(modifier = Modifier.height(8.dp))
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

@Composable
fun ParagraphContent(contentItems: List<ContentItem>) {
    val color = MaterialTheme.colorScheme.primary
    Text(
        text = buildAnnotatedString {
            contentItems.forEach { contentItem ->
                when {
                    contentItem.marks?.any { it.type == "link" } == true -> {
                        val linkAttrs = contentItem.marks.find { it.type == "link" }?.attrs
                        linkAttrs?.let { attr ->
                            attr.href?.let {
                                pushStringAnnotation(
                                    tag = "URL",
                                    annotation = it
                                )
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = color,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append(contentItem.text.orEmpty())
                            }
                            pop()
                        }
                    }

                    contentItem.marks?.any { it.type == "bold" } == true -> {
                        // Handle bold text
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900
                            )
                        ) {
                            append(contentItem.text.orEmpty())
                        }
                    }

                    else -> append(contentItem.text.orEmpty())
                }
            }
        },
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
        textAlign = TextAlign.Justify
    )
}

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
