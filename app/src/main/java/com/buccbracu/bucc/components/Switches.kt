package com.buccbracu.bucc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun DarkModeToggle(
    darkModeEnabled: Boolean,
    onChange: () -> Unit

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .padding(end = 10.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Switch(
            checked = darkModeEnabled,
            onCheckedChange = {
                onChange()
            },
            thumbContent = {
                if(darkModeEnabled){
                    GradientBGButton(
                        bgColor = GradientColors.MoonGradient,
                        imageVector = Icons.Filled.DarkMode,
                        contentDescription = "Dark Mode",
                        tint = Color.White
                    )
                }
                else{
                    GradientBGButton(
                        bgColor = GradientColors.SunGradient,
                        imageVector = Icons.Filled.Lightbulb,
                        contentDescription = "Light Mode",
                        tint = Color.Yellow
                    )
                }
            },
            modifier = Modifier
                .scale(0.7f)
        )
    }
}

@Composable
fun GradientBGButton(
    bgColor: Brush,
    imageVector: ImageVector,
    contentDescription: String,
    tint: Color
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ){
        Image(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize(0.8f),
            colorFilter = ColorFilter.tint(
                color = tint
            )
        )
    }
}