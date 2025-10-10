package com.test.myapplication.search.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.myapplication.CommonData.searchApp
import com.test.myapplication.CommonData.searchGame
import com.test.myapplication.R

@Composable
fun SearchGameSection() {
    Column {
        Text(
            text = "게임 검색",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
        )

        val searchGame = searchGame
        SimpleGrid(searchGame, "game")

        searchRecommend()

        Text(
            text = "앱  탐색",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 12.dp)
        )

        val searchApp = searchApp
        SimpleGrid(searchApp, "app")

        Spacer(modifier = Modifier
            .height(16.dp)
        )
    }

}

@Composable
fun searchRecommend() {

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = "추천",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // https://play-lh.googleusercontent.com/bpiLHv_Nvsa4Iv_KIfboxKBtKM6B5n19ftEdLq8iPFvSLSvgS_xjTrf4GMMmVE28hQ=s256-rw
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF7EFEF))
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model ="https://play-lh.googleusercontent.com/bpiLHv_Nvsa4Iv_KIfboxKBtKM6B5n19ftEdLq8iPFvSLSvgS_xjTrf4GMMmVE28hQ=s256-rw",
                contentDescription = "",
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "뱀피르",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 3.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFC4C9D1))
                            .padding(vertical = 3.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "신규",
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.DarkGray,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Netmarble · 롤플레잉",
                        fontSize = 10.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.DarkGray,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp, start = 3.dp)
                ) {
                    Text(
                        text = "4.0",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Image(
                        painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = "",
                        modifier = Modifier.size(11.dp),
                    )
                }


            }
        }
    }

}

@Composable
fun SimpleGrid(items: List<String>, type: String) {
    val columnCount = 2

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.padding(start = 24.dp, end = 24.dp)
    ) {
        items.chunked(columnCount).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                rowItems.forEach { item ->
                    GridItem(item = item, modifier = Modifier.weight(1f), type)
                }

                val remainingItems = columnCount - rowItems.size
                if (remainingItems > 0) {
                    for (i in 0 until remainingItems) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


@Composable
fun GridItem(item: String, modifier: Modifier = Modifier, type: String) {
    Row(
        modifier = modifier
            .aspectRatio(2.5f)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF7EFEF))
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Image(
            painterResource(id = if(type=="game") R.drawable.baseline_sports_tennis_24 else R.drawable.baseline_camera_alt_24),
            contentDescription = "",
            modifier = Modifier.size(24.dp),
        )
    }


}