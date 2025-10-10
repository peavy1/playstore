package com.test.myapplication.search.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.myapplication.R


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF7EFEF))
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.ic_search),
            contentDescription = "검색 아이콘",
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "앱 및 게임 검색",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.DarkGray,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painterResource(id = R.drawable.baseline_keyboard_voice_24),
            contentDescription = "음성 검색 아이콘",
            modifier = Modifier.size(20.dp),
        )
    }
}



@Composable
fun SearchHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.width(16.dp))

        ProfileIcon()
    }
}


@Composable
fun ProfileIcon() {
    Card(
        modifier = Modifier.size(35.dp),
        shape = CircleShape, // cardCornerRadius를 원형으로 처리
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF7E57C2) // cardBackgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp // cardElevation
        )
    ) {
        // TextView와 gravity="center"를 구현
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "P",
                color = Color.White,
                fontSize = 20.sp // sp 단위를 사용하는 것을 권장
            )
        }
    }
}