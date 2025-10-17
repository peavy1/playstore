package com.test.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.myapplication.CommonData.recommendCategory
import com.test.myapplication.util.AppUtil.randomApp
import com.test.myapplication.R
import com.test.myapplication.model.AppItem

@Composable
fun RecommendLazyRow(dataList: List<AppItem>) {
    recommendCategory.forEach {
        Column {
            Text(
                text = it,
                fontSize = 15.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                modifier = Modifier.padding(start = 28.dp, top = 34.dp, bottom = 10.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 28.dp)
            ) {
                items(randomApp(dataList, 5)) { appItem ->
                    Column {
                        AsyncImage(
                            model = appItem.imageUrl,
                            contentDescription = "",
                            modifier = Modifier
                                .size(105.dp)
                                .shadow(elevation = 2.dp, shape = RoundedCornerShape(17.dp))
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Text(
                            text = appItem.title,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black,
                            modifier = Modifier
                                .width(105.dp)
                                .padding(top = 10.dp, bottom = 5.dp)
                        )
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
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
        }

    }
}





