package com.test.myapplication.view.detail

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.test.myapplication.R
import com.test.myapplication.model.AppDetails

@Composable
fun TopSection(details: AppDetails) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.padding(start = 28.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
    ) {

        var isImageLoaded by remember(details.icon) { mutableStateOf(false) }

        val elevation by animateDpAsState(
            targetValue = if (isImageLoaded) 2.dp else 0.dp,
            animationSpec = tween(
                durationMillis = 50,
                delayMillis = 100
            ),
            label = "elevationAnimation"
        )

        val imageModifier = Modifier
            .size(65.dp)
            .shadow(
//                    elevation = if (isImageLoaded) 2.dp else 0.dp,
                elevation = elevation,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))

        val imageRequest = remember(details.icon) {
            ImageRequest.Builder(context)
                .data(details.icon)
                .crossfade(true)
                .allowRgb565(true)
                .build()
        }

        AsyncImage(
            model = imageRequest,
            contentDescription = "",
            onState = { state ->
                isImageLoaded = state is AsyncImagePainter.State.Success
            },
            modifier = imageModifier
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                fontSize = 19.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W200,
                text = details.title
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Blue,
                text = details.developer,
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                text = purchaseInfo(details.containsAds, details.offersInAppPurchases, context)
            )

        }

    }
}

fun purchaseInfo(containsAds: Boolean, offersInAppPurchases: Boolean, context: Context): String {
    return  buildList {
        if (containsAds) add(context.getString(R.string.containsAds))
        if (offersInAppPurchases) add(context.getString(R.string.inAppPurchases))
    }.joinToString(separator = " • ")
}

