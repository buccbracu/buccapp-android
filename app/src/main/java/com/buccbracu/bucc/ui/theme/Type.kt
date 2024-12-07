package com.buccbracu.bucc.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.R

// Define the FontFamily using the local variable font files
val outfitFontFamily = FontFamily(
    Font(R.font.outfitblack, weight = FontWeight.Black),
    Font(R.font.outfitbold, weight = FontWeight.Bold),
    Font(R.font.outfitextrabold, weight = FontWeight.ExtraBold),
    Font(R.font.outfitlight, weight = FontWeight.Light),
    Font(R.font.outfitmedium, weight = FontWeight.Medium),
    Font(R.font.outfitregular, weight = FontWeight.Normal),
    Font(R.font.outfitsemibold, weight = FontWeight.SemiBold),
    Font(R.font.outfitthin, weight = FontWeight.Thin),
    Font(R.font.outfitextralight, weight = FontWeight.ExtraLight)
)

// Default Material 3 typography values
val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(
        fontFamily = outfitFontFamily
    ),
    displayMedium = baseline.displayMedium.copy(
        fontFamily = outfitFontFamily
    ),
    displaySmall = baseline.displaySmall.copy(
        fontFamily = outfitFontFamily
    ),
    headlineLarge = baseline.headlineLarge.copy(
        fontFamily = outfitFontFamily
    ),
    headlineMedium = baseline.headlineMedium.copy(
        fontFamily = outfitFontFamily
    ),
    headlineSmall = baseline.headlineSmall.copy(
        fontFamily = outfitFontFamily
    ),
    titleLarge = baseline.titleLarge.copy(
        fontFamily = outfitFontFamily
    ),
    titleMedium = baseline.titleMedium.copy(
        fontFamily = outfitFontFamily
    ),
    titleSmall = baseline.titleSmall.copy(
        fontFamily = outfitFontFamily
    ),
    bodyLarge = baseline.bodyLarge.copy(
        fontFamily = outfitFontFamily
    ),
    bodyMedium = baseline.bodyMedium.copy(
        fontFamily = outfitFontFamily
    ),
    bodySmall = baseline.bodySmall.copy(
        fontFamily = outfitFontFamily
    ),
    labelLarge = baseline.labelLarge.copy(
        fontFamily = outfitFontFamily
    ),
    labelMedium = baseline.labelMedium.copy(
        fontFamily = outfitFontFamily
    ),
    labelSmall = baseline.labelSmall.copy(
        fontFamily = outfitFontFamily
    )
)