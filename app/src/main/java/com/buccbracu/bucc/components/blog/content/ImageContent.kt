package com.buccbracu.bucc.components.blog.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

private fun ensureHttps(url: String): String {
    return if (url.startsWith("http://")) {
        url.replaceFirst("http://", "https://")
    } else if (!url.startsWith("http") && url.isNotBlank()) {
        "https://$url" // Add https if the URL doesn't have any protocol
    } else {
        url // Return as is if already https or empty
    }
}

@Composable
fun ImageContent(url: String){
    val src = ensureHttps(url)
    AsyncImage(
        model = src,
        contentDescription = "Blog Image",
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.FillWidth,
    )
}