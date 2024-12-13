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

    val ClubGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3B82F6), // Blue-500
            Color(0xFF2DD4BF)  // Teal-400
        )
    )

    @Composable
    fun animatedClubGradient(offset: Float): Brush {
        return remember(offset) {
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFF2DD4BF),
                    Color(0xFF3B82F6),
                    Color(0xFF2DD4BF),

                ),
                start = Offset(1000f * offset, 0f),
                end = Offset(1000f * (offset + 1), 0f),
                tileMode = TileMode.Repeated
            )
        }

    }

}
