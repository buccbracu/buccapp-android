package com.buccbracu.bucc.components.blog

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.Content

@Composable
fun HeadingContent(content: Content, fontSize: TextUnit = 14.sp) {
    val headingLevel = content.attrs?.level ?: 1
    var textStyle = when (headingLevel) {
        1 -> MaterialTheme.typography.headlineLarge
        2 -> MaterialTheme.typography.headlineMedium
        else -> MaterialTheme.typography.headlineSmall
    }
    val adjustedFontSize = (textStyle.fontSize.value + (fontSize.value - 14)).sp

// Create a new TextStyle with the updated font size
    val adjustedTextStyle = textStyle.copy(fontSize = adjustedFontSize)



    content.content?.forEach { contentItem ->
        if (contentItem.type == "text") {
            Text(
                text = contentItem.text.orEmpty(),
                style = adjustedTextStyle,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}