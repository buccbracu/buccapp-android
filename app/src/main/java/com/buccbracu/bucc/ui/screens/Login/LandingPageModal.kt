package com.buccbracu.bucc.ui.screens.Login

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.vector.AnimatedImageVector
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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.lerp
import com.buccbracu.bucc.components.AnimatedVector
import com.buccbracu.bucc.ui.theme.Navy
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun LandingPageModal() {

    var swipeOffset by remember { mutableStateOf(0f) }
    val maxOffset = 1000f


    var isSwipedUp by remember {
        mutableStateOf(false)
    }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    // Triggered animation values
    val imageScale by animateFloatAsState(targetValue = if (isSwipedUp) 0.5f else 1f)
    val imageOffsetY by animateDpAsState(targetValue = if (isSwipedUp) 200.dp else 0.dp)
    val contentOpacity by animateFloatAsState(
        targetValue = if (isSwipedUp) 0f else 1f,
        animationSpec = tween(
            durationMillis = 300, // Adjust this value for faster or slower fade
            easing = LinearOutSlowInEasing // Customize easing if desired
        )
    )


    LaunchedEffect(scaffoldState.bottomSheetState.currentValue) {

        scope.launch{
            if (scaffoldState.bottomSheetState.currentValue.toString() != "Expanded" && isSwipedUp) {
                isSwipedUp = false
            }
        }
    }
    println(scaffoldState.bottomSheetState.currentValue.toString())



    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Box(
                modifier = Modifier
                    .height(550.dp)
            ){
                LoginScreen()
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgColor)
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->
                        // Update swipe offset only for upward swipe (dragAmount is negative)
                        if (dragAmount < -50) { // Negative value indicates upward swipe
                            isSwipedUp = true // Trigger the full animation
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }

                        } else {
                            isSwipedUp = false
                            scope.launch {
                                scaffoldState.bottomSheetState.partialExpand()
                            }
                        }
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 70.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = logoImg),
                    contentDescription = "BUCC Logo",
                    modifier = Modifier
                        .size(400.dp)
                        .graphicsLayer(
                            scaleX = imageScale,
                            scaleY = imageScale,
                            translationY = -imageOffsetY.value
                        )


                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(contentOpacity),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    //            Spacer(modifier = Modifier.height(150.dp))
                    Text(
                        text = "BRAC University Computer Club",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        lineHeight = 32.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(50.dp))
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


            }
        }
    }


}

