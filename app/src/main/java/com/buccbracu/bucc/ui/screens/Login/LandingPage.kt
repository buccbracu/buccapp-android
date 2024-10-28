package com.buccbracu.bucc.ui.screens.Login

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import com.buccbracu.bucc.R
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import com.buccbracu.bucc.components.AnimatedVector
import com.buccbracu.bucc.ui.theme.Navy
import kotlinx.coroutines.launch

var darkMode = false

var bgColor = if(darkMode == false) Color.White else Navy
val logoImg = R.drawable.bucc

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage() {
    var isSwipedUp by remember { mutableStateOf(false) }
    var swipeProgress by remember { mutableFloatStateOf(0f) } // Track the swipe progress (0 to 1)
    val scope = rememberCoroutineScope()

    val swipeThreshold = 1000f
    val minSwipeToShow = 0.5f

    val imageScale by animateFloatAsState(targetValue = 1.2f - (swipeProgress * 0.4f)) // Scale between 1.2 and 0.8
    val contentOpacity by animateFloatAsState(targetValue = 1f - swipeProgress) // Opacity from 1 to 0
    val reverseContentOpacity by animateFloatAsState(targetValue = swipeProgress) // Reverse opacity

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { _, dragAmount ->
                        // Update the swipe offset based on the drag amount
                        swipeProgress = (swipeProgress - (dragAmount / swipeThreshold)).coerceIn(0f, 1f)
                    },
                    onDragEnd = {
                        // When the user releases the swipe
                        if (swipeProgress >= minSwipeToShow) {
                            // If swipe progress is greater than the threshold, complete the swipe
                            swipeProgress = 1f
                            isSwipedUp = true
                        } else {
                            // If swipe progress is less than the threshold, reset the swipe
                            swipeProgress = 0f
                            isSwipedUp = false
                        }
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = logoImg),
                contentDescription = "BUCC Logo",
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = imageScale,
                        scaleY = imageScale
                    )
            )

            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(contentOpacity),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "BRAC University Computer Club",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        lineHeight = 32.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(150.dp))
                    AnimatedVector("up_new.json")
                    Text(
                        text = "Swipe Up to",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Text(
                        text = "Upgrade Yourself",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(reverseContentOpacity),
                ) {
                    LoginScreen()
                }
            }
        }
    }
}