package com.buccbracu.bucc.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable

fun AnimatedVector(asset: String){
    DotLottieAnimation(

        source = DotLottieSource.Asset(asset), // from asset .lottie / .json
//        source = DotLottieSource.Json("{"v":"4.8.0","meta":{"g":"LottieFiles .........."), // lottie json string
//        source = DotLottieSource.Data(ByteArray), // dotLottie data as ByteArray
        autoplay = true,
        loop = true,
        speed = 1f,
        useFrameInterpolation = false,
        playMode = Mode.FORWARD,
        modifier = Modifier
            .rotate(180f)
            .size(150.dp)

    )

}

@Composable
fun AnimatedIcon(
    initialValue: Float,
    targetValue: Float,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    repeatMode: RepeatMode = RepeatMode.Restart
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offsetX by infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = LinearEasing),
            repeatMode = repeatMode
        ),
        label = ""
    )

    Icon(
        imageVector = icon,
        contentDescription = "Animated icon",
        modifier = modifier
            .offset(x = offsetX.dp),
    )
}

