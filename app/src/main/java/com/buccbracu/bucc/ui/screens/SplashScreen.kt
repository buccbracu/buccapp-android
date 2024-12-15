package com.buccbracu.bucc.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.ui.theme.backgroundDark
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    setSession: (Session?) -> Unit
) {
    // State for controlling the animation of the motto image
    val loginVM: LoginVM = hiltViewModel()
    val sessionData by loginVM.session.collectAsState()
    val animationProgress = remember { Animatable(0f) }
    val contentOpacity by animateFloatAsState(targetValue = animationProgress.value)
    val baseLogo = R.drawable.bucc_wide_white_letters_no_motto
    val motto = R.drawable.upgrade_yourself_white
    val screenWidth = LocalConfiguration.current.screenWidthDp

    // Launch the animation when the SplashScreen composable is displayed
    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )

        setSession(sessionData)
        navController.navigate("main") {
            popUpTo("Splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark),
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(backgroundDark)
                .padding(top = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            // Base logo
            Image(
                painter = painterResource(id = baseLogo), // Replace with your logo resource
                contentDescription = "Base Logo",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                contentScale = ContentScale.Fit
            )

            // Motto image
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .alpha(contentOpacity)
                    .offset {
                        IntOffset(
                            (animationProgress.value * screenWidth ).toInt(),
                            0
                        )
                    }
                    .padding(top = 90.dp, end = 15.dp)

            ) {
                Image(
                    painter = painterResource(id = motto), // Replace with your motto resource
                    contentDescription = "Motto",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .height(30.dp),
                )
            }
        }
    }
}
