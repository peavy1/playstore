package com.test.myapplication.game.view

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
import com.test.myapplication.CommonData.items
import com.test.myapplication.model.SlideItem
import java.time.format.TextStyle


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselPagerSample() {
    val pagerState = rememberPagerState(pageCount = { items.size })

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
                model = items[page].slideImage,
                contentDescription = "페이지 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 8.dp)) {
                Text(text = items[page].title, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = items[page].subTitle, fontSize = 12.sp, color = Color.White)
            }
        }

    }
}





/*@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselPagerSample() {
    val slideItems = listOf("페이지 1", "페이지 2", "페이지 3", "페이지 4")
    val pagerState = rememberPagerState(pageCount = { slideItems.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .aspectRatio(648f / 364f)
            .fillMaxWidth(),
        pageSpacing = 16.dp,
        contentPadding = PaddingValues(horizontal = 32.dp)
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF, (200 - page * 40), (100 + page * 40)))
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = slideItems[page],
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}*/
