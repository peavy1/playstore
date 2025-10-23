package com.test.myapplication.searchresult.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.test.myapplication.searchresult.SearchPageType
import com.test.myapplication.searchresult.SearchPageViewModel

@Composable
fun SearchResultScreenShotScreen (
             screenList: List<String>,
             navController: NavHostController? = null,
             viewModel: SearchPageViewModel? = null
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 28.dp)
    ) {


        itemsIndexed(screenList) { index, imageUrl ->
            val placeholderRatio = 16f / 9f
            var imageRatio by remember { mutableStateOf(placeholderRatio) }

            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(
                        imageRatio
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        navController?.let {
                            viewModel?.screenList = screenList
                            viewModel?.screenShotIndex = index
                            navController.navigate(SearchPageType.Screen.route)
                        }
                    },
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
}