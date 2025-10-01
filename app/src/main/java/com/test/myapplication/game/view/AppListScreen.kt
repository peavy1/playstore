package com.test.myapplication.game.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.test.myapplication.R
import com.test.myapplication.game.viewmodel.GameSharedViewModel
import com.test.myapplication.model.AppItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect




@Composable
fun AppListScreen(sharedViewModel: GameSharedViewModel) {

    val appList by sharedViewModel.appRankList.collectAsState()
    val listState = rememberLazyListState()
    val isLoading by sharedViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        if (appList.isEmpty()) {
            sharedViewModel.loadNextPage()
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(rememberNestedScrollInteropConnection())
    ) {
        Log.d("peavyaaa", "Aaa")
        itemsIndexed(
            items = appList
        ) { index, item ->
            ListItem(index, item)
        }

        if (isLoading && appList.isNotEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }


    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                val totalItemsCount = layoutInfo.totalItemsCount

                if (!isLoading && totalItemsCount > 0 && lastVisibleItemIndex + 5 >= totalItemsCount) {
                    sharedViewModel.loadNextPage()
                }
            }
    }
}

@Composable
fun ListItem(index: Int, appItem: AppItem) {
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

@Composable
fun Dp.toPx(): Int {
    return with(LocalDensity.current) { this@toPx.toPx() }.toInt()
}





