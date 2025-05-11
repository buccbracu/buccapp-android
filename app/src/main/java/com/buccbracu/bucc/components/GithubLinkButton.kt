package com.buccbracu.bucc.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

@Composable
fun GitHubLinkButton(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    TextButton(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW,
                "https://github.com/buccbracu/buccapp-android".toUri())
            context.startActivity(intent)
        },
        modifier = modifier
    ) {
        Text(
            text = "Visit BUCC Android App's GitHub",
            color = Color(0xFF4169E1),
            textDecoration = TextDecoration.Underline,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
