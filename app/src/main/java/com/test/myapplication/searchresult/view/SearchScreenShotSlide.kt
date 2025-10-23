package com.test.myapplication.searchresult.view

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.myapplication.CommonData.sliderItems
import com.test.myapplication.model.AppSummary
import com.test.myapplication.model.SearchUiState
import com.test.myapplication.searchresult.SearchPageViewModel

@Composable
fun SearchScreenShotSlide(screenShot : List<String>, initialPage: Int) {

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { screenShot.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
    ) { page ->

        val placeholderRatio = 16f / 9f
        var imageRatio by remember { mutableStateOf(placeholderRatio) }


        AsyncImage(
            model = screenShot[page],
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(
                    imageRatio
                ),
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