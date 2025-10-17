package com.test.myapplication.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.myapplication.CommonData.sliderItems
import com.test.myapplication.model.SlideItem
import java.time.format.TextStyle


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselPagerSample() {
    val pagerState = rememberPagerState(pageCount = { sliderItems.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .aspectRatio(648f / 364f)
            .fillMaxWidth(),
        pageSpacing = 16.dp,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) { page ->

        Box() {
            AsyncImage(
                model = sliderItems[page].slideImage,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 8.dp)) {
                Text(text = sliderItems[page].title, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = sliderItems[page].subTitle, fontSize = 12.sp, color = Color.White)
            }
        }

    }
}
