package com.test.myapplication.game.view

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.test.myapplication.model.AppItem



@Composable
fun MyComposeListScreen() {
    val items = (1..100).map { "아이템 #$it" }

    // ⭐️⭐️⭐️ 핵심: LazyColumn에 nestedScroll Modifier 추가 ⭐️⭐️⭐️
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            // 이 한 줄이 컴포즈의 스크롤을 바깥 XML의 CoordinatorLayout과 연결해줍니다.
            .nestedScroll(rememberNestedScrollInteropConnection())
    ) {
        items(items) { item ->
            ListItemTest(text = item)
        }
    }
}


@Composable
fun AppListScreen(appList: List<AppItem>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(rememberNestedScrollInteropConnection())
    ) {
//        items(appList) { item ->
//            ListItem(appItem = item)
//        }
//

        itemsIndexed(appList) { index, item ->
            ListItem(index, item)
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

        var isImageLoaded by remember { mutableStateOf(false) }

        val imageModifier = Modifier
            .size(56.dp)
            .then(
                if (isImageLoaded) {
                    Modifier.shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
                } else {
                    Modifier
                }
            )
            .clip(RoundedCornerShape(16.dp))

       /* AsyncImage(
            model = appItem.imageUrl,
            contentDescription = "",
            onState = { state ->
                isImageLoaded = state is AsyncImagePainter.State.Success
            },
            modifier = imageModifier
        )*/

        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(appItem.imageUrl)
            .crossfade(true)
            .allowRgb565(true)
            .size(56.dp.toPx())
            .build()
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
                color = Color.Black
            )

            Text(
                text = appItem.rating.toString(),
                color = Color.Black
            )
        }


    }

}

@Composable
fun Dp.toPx(): Int {
    return with(LocalDensity.current) { this@toPx.toPx() }.toInt()
}

/*
@Composable
fun ListItem(index: Int, appItem: AppItem) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = (index+1).toString(),
            color = Color.Black,
            modifier = Modifier
                .padding(start = 30.dp, end = 8.dp)
        )


        AsyncImage(
            model = appItem.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .size(55.dp)
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)) // 그림자 추가
                .clip(RoundedCornerShape(16.dp))
        )

    }

    */



/*
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

        var isImageLoaded by remember { mutableStateOf(false) }

        val imageModifier = Modifier
            .size(56.dp)
            .then(
                if (isImageLoaded) {
                    Modifier.shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp))
                } else {
                    Modifier
                }
            )
            .clip(RoundedCornerShape(16.dp))

        AsyncImage(
            model = appItem.imageUrl,
            contentDescription = "",
            onState = { state ->
                isImageLoaded = state is AsyncImagePainter.State.Success
            },
            modifier = imageModifier
        )



    }

}
*/


@Composable
fun ListItemTest(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
