package com.buccbracu.bucc.components.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.Content
import com.buccbracu.bucc.backend.remote.models.ContentItem

@Composable
fun OrderedListContent(content: Content, fontSize: TextUnit = 14.sp) {
    val items = content.content.orEmpty()
    val startIndex = content.attrs?.start ?: 1 // Default start index is 1

    Column(modifier = Modifier.padding(start = 16.dp)) {
        items.forEachIndexed { index, listItem ->
            if (listItem.type == "listItem") {
                // Display the list number
                Row{
                    Text(
                        text = "${startIndex + index}.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 5.dp, end = 8.dp),
                        fontWeight = FontWeight.Bold
                    )

                    // Render the content of the listItem
                    listItem.content?.forEach { paragraph ->
                        if (paragraph.type == "paragraph") {
                            RenderParagraph(paragraph.content.orEmpty(), fontSize)
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun RenderParagraph(contentItems: List<ContentItem>, fontSize: TextUnit = 14.sp) {
    val color = MaterialTheme.colorScheme.primary
    Text(
        text = buildAnnotatedString {
            contentItems.forEach { contentItem ->
                when {
                    contentItem.marks?.any { it.type == "link" } == true -> {
                        val linkAttrs = contentItem.marks.find { it.type == "link" }?.attrs
                        linkAttrs?.href?.let { href ->
                            pushStringAnnotation(tag = "URL", annotation = href)
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(contentItem.text.orEmpty())
                        }
                    }
                    contentItem.type == "hardBreak" -> {
                        append("\n")
                    }
                    else -> append(contentItem.text.orEmpty())
                }
            }
        },
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Justify,
        fontSize = fontSize
    )
}