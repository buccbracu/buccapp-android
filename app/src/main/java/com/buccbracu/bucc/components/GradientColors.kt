package com.buccbracu.bucc.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

object GradientColors {
    val SunGradient = Brush.radialGradient(
        colors = listOf(
            Color.Yellow,          // Core of the sun
            Color(0xFFFFA726),     // Warm orange glow
            Color(0xFFFF7043)      // Fading reddish-orange
        )
    )

    val MoonGradient = Brush.radialGradient(
        colors = listOf(
            Color(0xFFB0C4DE), // Light steel blue for the moon's surface
            Color(0xFF1C1C1C)  // Dark gray to simulate the night sky
        )
    )
    @Composable
    fun animatedGradient(
        offset: Float,
        colors: List<Color>,
        mode: TileMode
    ): Brush {
        return remember(offset) {
            Brush.linearGradient(
                colors = colors,
                start = Offset(1000f * offset, 0f),
                end = Offset(1000f * (offset + 1), 0f),
                tileMode = mode
            )
        }
    }

}
