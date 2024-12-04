package com.buccbracu.bucc.components.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.backend.remote.models.Content
import com.buccbracu.bucc.backend.remote.models.ContentItem
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp


//@Composable
//fun TodoListContent(contentItems: List<ContentItem>) {
//    Column(modifier = Modifier.padding(8.dp)) {
//        contentItems.forEach { item ->
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Checkbox(
//                    checked = item.marks?.completed ?: false,
//                    onCheckedChange = { /* Handle state change if needed */ }
//                )
//                Text(
//                    text = item.text.orEmpty(),
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//            }
//        }
//    }
//}

@Composable
fun BulletListContent(listItems: List<ContentItem>) {
    Column(modifier = Modifier.padding(8.dp)) {
        listItems.forEach { item ->
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text("•", modifier = Modifier.padding(end = 8.dp))
                Text(text = item.text.orEmpty(), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}



//@Composable
//fun OrderedListContent(listItems: List<ContentItem>) {
//    Column(modifier = Modifier.padding(8.dp)) {
//        listItems.forEachIndexed { index, item ->
//            Row(modifier = Modifier.padding(vertical = 4.dp)) {
//                Text("${index + 1}.", modifier = Modifier.padding(end = 8.dp))
//                Text(text = item.text.orEmpty(), style = MaterialTheme.typography.bodyMedium)
//            }
//        }
//    }
//}

@Composable
fun QuoteContent(content: Content) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(
            text = content.content?.joinToString(separator = "\n") { it.text.orEmpty() } ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun CodeContent(content: Content) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF2E3440), RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(
            text = content.content?.joinToString(separator = "\n") { it.text.orEmpty() } ?: "",
            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF88C0D0)),
            fontFamily = FontFamily.Monospace
        )
    }
}
