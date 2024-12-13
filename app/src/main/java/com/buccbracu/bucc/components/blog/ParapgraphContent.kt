package com.buccbracu.bucc.components.blog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.ContentItem

@Composable
fun ParagraphContent(contentItems: List<ContentItem>, fontSize: TextUnit = 15.sp) {
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
        textAlign = TextAlign.Justify,
        fontSize = fontSize
    )
}