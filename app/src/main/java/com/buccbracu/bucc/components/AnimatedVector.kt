package com.buccbracu.bucc.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
            .size(100.dp)
    )

}

