package com.buccbracu.bucc.components.blog.content

import androidx.compose.foundation.layout.padding
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
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            )
            view
        },
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

//@Composable
//fun YoutubeContent(url: String) {
//    val context = LocalContext.current
//    val videoId = extractVideoId(url)
//    var isLoading by remember { mutableStateOf(true) } // State to track loading status
//
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(10.dp))
//            .fillMaxWidth() // Adjust the size to fit your layout
//            .aspectRatio(16f / 9f) // Ensures a standard video aspect ratio
//    ) {
//        // YouTube Player
//        AndroidView(
//            factory = {
//                val view = YouTubePlayerView(it)
//                view.addYouTubePlayerListener(
//                    object : AbstractYouTubePlayerListener() {
//                        override fun onReady(youTubePlayer: YouTubePlayer) {
//                            super.onReady(youTubePlayer)
//                            youTubePlayer.loadVideo(videoId, 0f)
//                            isLoading = false // Video is ready, hide the loading indicator
//                        }
//                    }
//                )
//                view
//            },
//            modifier = Modifier.fillMaxSize()
//        )
//
//        // Loading Placeholder
//        if (isLoading) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Black.copy(alpha = 0.5f)), // Semi-transparent overlay
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator(
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//        }
//    }
//}
