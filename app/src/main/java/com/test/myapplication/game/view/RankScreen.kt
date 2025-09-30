package com.test.myapplication.game.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.test.myapplication.game.viewmodel.GameSharedViewModel
import com.test.myapplication.model.AppItem

@Composable
fun RankView(
    appList1: List<List<AppItem>>,
    appList2: List<List<AppItem>>,
    appList3: List<List<AppItem>>
) {

     Column(
         modifier = Modifier.padding(top = 34.dp, bottom = 10.dp)
     ) {

         var type1 by remember { mutableStateOf(true) }
         var type2 by remember { mutableStateOf(false) }
         var type3 by remember { mutableStateOf(false) }

         Text(
             text = if(type1) "인기 무료" else if(type2) "최고 매출" else "인기 유료",
             fontSize = 15.sp,
             fontFamily = FontFamily.Cursive,
             color = Color.Black,
             modifier = Modifier.padding(start = 28.dp)
         )

         Spacer(modifier = Modifier.height(10.dp))

         Row(
             modifier = Modifier.padding(start = 28.dp)
         ) {
             Row(
                 modifier = Modifier
                     .clip(RoundedCornerShape(12.dp))
//                                .background(Color(0xFFEEEEEE))
//                     .background(Color(0xFFF7EFEF))
                     .background(if (type1) Color(0xFFF7EFEF) else Color(0xFFCFD5DD))
                     .padding(horizontal = 10.dp, vertical = 6.dp)
                     .clickable {
                         type1 = true
                         type2 = false
                         type3 = false
                     },
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Text(
                     text = "인기 무료",
                     fontSize = 13.sp,
                     fontFamily = FontFamily.SansSerif,
                     color = Color.DarkGray,
                 )
             }

             Spacer(modifier = Modifier.width(8.dp))

             Row(
                 modifier = Modifier
                     .clip(RoundedCornerShape(12.dp))
                     .background(if (type2) Color(0xFFF7EFEF) else Color(0xFFCFD5DD))
                     .padding(horizontal = 10.dp, vertical = 6.dp)
                     .clickable {
                         type1 = false
                         type2 = true
                         type3 = false
                     },
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Text(
                     text = "최고 매출",
                     fontSize = 13.sp,
                     fontFamily = FontFamily.SansSerif,
                     color = Color.DarkGray,
                 )
             }

             Spacer(modifier = Modifier.width(8.dp))

             Row(
                 modifier = Modifier
                     .clip(RoundedCornerShape(12.dp))
                     .background(if (type3) Color(0xFFF7EFEF) else Color(0xFFCFD5DD))
                     .padding(horizontal = 10.dp, vertical = 6.dp)
                     .clickable {
                         type1 = false
                         type2 = false
                         type3 = true
                     },
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Text(
                     text = "인기 유료",
                     fontSize = 13.sp,
                     fontFamily = FontFamily.SansSerif,
                     color = Color.DarkGray,
                 )
             }
         }

         //
         Spacer(modifier = Modifier.height(10.dp))


         if(type1) {
             RankList(appList1)
         } else if(type2) {
             RankList(appList2)
         } else if(type3) {
             RankList(appList3)
         }
     }


}

@Composable
fun RankList(
    appList: List<List<AppItem>>,
) {

    LazyRow(

    ) {

        itemsIndexed(appList) {columnIndex, chunked ->
            Column {
                chunked.forEachIndexed { rowIndex, it ->
                    val rank = columnIndex * 3 + rowIndex + 1

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = (rank).toString(),
                            color = Color.Black,
                            modifier = Modifier
                                .offset(y = 2.dp)
                                .padding(start = 32.dp, end = 16.dp)
                        )

                        var isImageLoaded by remember { mutableStateOf(false) }

                        val imageModifier = Modifier
                            .size(56.dp)
                            .then(
                                if (isImageLoaded) {
                                    Modifier.shadow(
                                        elevation = 2.dp,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                } else {
                                    Modifier
                                }
                            )
                            .clip(RoundedCornerShape(16.dp))

                        val imageRequest = ImageRequest.Builder(LocalContext.current)
                            .data(it.imageUrl)
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
                                text = it.title,
                                color = Color.Black,
                                modifier = Modifier.width(200.dp)
                            )

                            Text(
                                text = it.rating,
                                color = Color.Black
                            )
                        }

                    }
                }
            }
        }
    }
}