package com.buccbracu.bucc.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.ui.theme.outfitFontFamily

@Composable
fun GradientText(
    text: String, fontSize:
    TextUnit = 12.sp,
    colors: List<Color>,
    mode: TileMode
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label= ""
    )

    val gradientBrush = GradientColors.animatedGradient(gradientOffset, colors, mode)


    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            brush = gradientBrush,
            fontFamily = outfitFontFamily
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .fillMaxWidth(),

        )

}