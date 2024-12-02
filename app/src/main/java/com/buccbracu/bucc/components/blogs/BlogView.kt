package com.buccbracu.bucc.components.blogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.backend.remote.models.Blog

/*
1. Image view
2. Proper Formatting of heading and subheading
3. Anchor texts fix
 */

@Composable
fun BlogView(blog: Blog) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Title
        Text(
            text = blog.title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Description
        Text(
            text = blog.description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        // Author
        Text(
            text = "By ${blog.author}",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Content
        blog.content.forEach { contentItem ->
            RenderContent(contentItem)
        }
    }
}

