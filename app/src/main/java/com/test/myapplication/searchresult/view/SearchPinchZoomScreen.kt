package com.test.myapplication.searchresult.view

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
fun PinchZoom(imageUrl: String, ratio: Float) {

    val width  = Resources.getSystem().displayMetrics.widthPixels / 2f
    val height = Resources.getSystem().displayMetrics.heightPixels / 2f

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, _ ->
                        Log.e("TAG", "MainScaffold: pan: $pan", )
                        scale *= zoom
                        /**
                         * zoom -> r
                         * centroid.x -> x_z
                         * offset.x -> x_o
                         * width -> screenWidth
                         */
                        offset += Offset(
                            x = (1 - zoom) * (centroid.x - offset.x - width),
                            y = (1 - zoom) * (centroid.y - offset.y - height)
                        ) + pan
                    }
                }
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
        ) {
            AsyncImage(
                model = imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(ratio)
            )
        }
    }
}