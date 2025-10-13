package com.test.myapplication.book.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.myapplication.util.AppUtil
import com.test.myapplication.CommonData.bookTabLazyRowData
import com.test.myapplication.model.AppItem

@Composable
fun BookLazyRow(dataList: List<AppItem>) {


    bookTabLazyRowData.forEach {

        Column {
            Text(
                text = it.first,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                modifier = Modifier.padding(start = 28.dp, top = 34.dp)
            )

            if(it.second.isNotEmpty()) {
                Text(
                    text = it.second,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 28.dp, top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 28.dp)
            ) {
                items(AppUtil.randomApp(dataList, 5)) { appItem ->
                    Column {
                        AsyncImage(
                            model = appItem.imageUrl,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(100.dp)
                                .aspectRatio(170f / 256f)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Text(
                            text = appItem.title,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black,
                            modifier = Modifier
                                .width(105.dp)
                                .padding(top = 10.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }

    }

}