package com.test.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.test.myapplication.R
import com.test.myapplication.model.AppItem

@Composable
fun ItemApp(index: Int, appItem: AppItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = (index + 1).toString(),
            color = Color.Black,
            modifier = Modifier
                .offset(y = 2.dp)
                .padding(start = 30.dp, end = 16.dp)
        )

        var isImageLoaded by remember(appItem.imageUrl) { mutableStateOf(false) }


        val imageModifier = Modifier
            .size(56.dp)
            .shadow(
                elevation = if (isImageLoaded) 2.dp else 0.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))


        val context = LocalContext.current
        val imageRequest = remember(appItem.imageUrl) {
            ImageRequest.Builder(context)
                .data(appItem.imageUrl)
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

        Column(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(start = 16.dp)
        ) {
            Text(
                text = appItem.title,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 5.dp)
            )
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
//                                .background(Color(0xFFEEEEEE))
                    .background(Color(0xFFF7EFEF))
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = appItem.rating,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.DarkGray,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = "",
                    modifier = Modifier.size(10.dp),
                )
            }
        }
    }
}