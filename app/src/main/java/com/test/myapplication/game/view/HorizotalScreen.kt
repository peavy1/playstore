package com.test.myapplication.game.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleLazyRowSample() {
    val appNames = listOf(
        "카카오톡", "Instagram", "네이버 지도", "YouTube", "쿠팡",
        "배달의민족", "토스", "오늘의집"
    )

    LazyRow {
        items(appNames) { name ->
            // 각 아이템 UI
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp) // 아이템 크기
            ) {
                Text(text = name)
            }
        }
    }
}