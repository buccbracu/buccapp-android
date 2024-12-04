package com.buccbracu.bucc.components.blogs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

fun extractVideoId(url: String): String {
    val prefix = "watch?v="
    return url.substringAfter(prefix, "").takeIf { it.isNotEmpty() } ?: ""
}



@Composable
fun YoutubeContent(url:String) {
    val context = LocalContext.current
    val videoId = extractVideoId(url)
    AndroidView(
        factory = {
            val view = YouTubePlayerView(it)
            val fragment = view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer:
                                         YouTubePlayer
                    ) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                }
            )
            view
        },
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
    )
}
