package com.buccbracu.bucc.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.BASE
import com.buccbracu.bucc.backend.remote.BASE_URL
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun NoInternet() {
    val config = LocalConfiguration.current
    var screenWidth by remember { mutableStateOf(config.screenWidthDp.toFloat()) }
    var screenHeight by remember { mutableStateOf(config.screenHeightDp.toFloat()) }

    // Current position
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    // Movement direction
    var directionX by remember { mutableStateOf(1f) }
    var directionY by remember { mutableStateOf(1f) }

    // Define animation speed and frame rate
    val frameDelay = 16L // ~60fps
    val speed = 3f // Adjust speed of the animation

    // Continuously update position
    LaunchedEffect(Unit) {
        while (true) {
            // Update position based on direction
            offsetX += speed * directionX
            offsetY += speed * directionY

            // Reverse direction when the column touches the container's edges
            if (offsetX <= 0f) {
                println("START X: OFFSET: $offsetX $screenWidth")
                println("Y: OFFSET: $offsetY $screenHeight")
                offsetX = 0f
                directionX = 1f
            } else if (offsetX >= screenWidth-offsetX) { // Subtracting column width
                println("END X: OFFSET: $offsetX $screenWidth")
                println("Y: OFFSET: $offsetY $screenHeight")
                offsetX = screenWidth-offsetX
                directionX = -1f
            }

            if (offsetY <= 0f) {
                println("TOP X: OFFSET: $offsetX $screenWidth")
                println("Y: OFFSET: $offsetY $screenHeight")
                offsetY = 0f
                directionY = 1f
            } else if (offsetY >= screenHeight-200) { // Subtracting column height
                println("BOTTOM X: OFFSET: $offsetX $screenWidth")
                println("Y: OFFSET: $offsetY $screenHeight")
                offsetY = screenHeight-200
                directionY = -1f
            }

            // Wait for the next frame
            kotlinx.coroutines.delay(frameDelay)
        }
    }

    // Parent container to detect screen size
    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val size = it.size
                screenWidth = size.width.toFloat()
                screenHeight = size.height.toFloat()
            }
            .border(1.dp, Color.Green),
        contentAlignment = Alignment.TopStart // Start position for bouncing
    ) {
        // Bouncing column
        Column(
            modifier = Modifier
                .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) } // Apply current offset
                .width(200.dp) // Fixed width
                .height(100.dp)
                .border(1.dp, Color.Cyan), // Fixed height
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No Internet",
                fontSize = 24.sp,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}



// Extension to convert DP to pixels
fun Float.dpToPx(): Float = this * (160 / 72f)



suspend fun checkServer(): Boolean {
    return withContext(Dispatchers.IO) {
        isReachable(BASE)
    }
}suspend fun checkGithub(): Boolean {
    return withContext(Dispatchers.IO) {
        isReachable("https://github.com")
    }
}

private fun isReachable(urlString: String): Boolean {
    return try {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 5000 // 5 seconds timeout
        connection.requestMethod = "HEAD"
        connection.connect()
        connection.responseCode == HttpURLConnection.HTTP_OK
    } catch (e: Exception) {
        false
    }
}

