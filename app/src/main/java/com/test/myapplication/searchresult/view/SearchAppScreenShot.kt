package com.test.myapplication.searchresult.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun SearchResultScreenShotScreen (
    video: String?,
    videoImage:  String?,
    screenList: List<String>,
    onScreenshotClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 28.dp),
        modifier = modifier
    ) {

        val placeholderRatio = 16f / 9f

        video?.let {
            item {

                var showPlayer by remember { mutableStateOf(false) }

                Box(modifier = Modifier
                    .fillParentMaxHeight()
                    .aspectRatio(placeholderRatio)
                    .clip(RoundedCornerShape(8.dp))) {

                    if(showPlayer) {
                        YoutubePlayer(
                            videoId = getVideoId(it),
                        )
                    } else {
                        AsyncImage(
                            model = videoImage,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.4f))
                                .clickable {
                                    showPlayer = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }
            }
        }

        itemsIndexed(screenList) { index, imageUrl ->
            var imageRatio by remember { mutableFloatStateOf(placeholderRatio) }

            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(
                        imageRatio
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            color = Color.White)
                    ) {
                        onScreenshotClick.invoke(index)
                    },
                onSuccess = { state ->
                    val drawable = state.result.drawable
                    val width = drawable.intrinsicWidth
                    val height = drawable.intrinsicHeight

                    if (height > 0) {
                        imageRatio = width.toFloat() / height.toFloat()
                    }
                }
            )

        }
    }
}

fun getVideoId(videoId: String): String {
    return videoId.substringAfter("embed/").substringBefore("?ps=play")
}

@Composable
fun YoutubePlayer(
    modifier: Modifier = Modifier,
    videoId: String,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val context = LocalContext.current
    val youTubePlayerView = remember {
        YouTubePlayerView(context).apply {
            lifecycleOwner.lifecycle.addObserver(this)

            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }

                override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                }
            })
        }
    }

    AndroidView(
        factory = { youTubePlayerView },
        modifier = modifier
    )

    DisposableEffect(Unit) {
        onDispose {
            youTubePlayerView.release()
        }
    }
}


