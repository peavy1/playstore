package com.test.myapplication.game.view


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.myapplication.R

@Composable
fun Greeting(name: String) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "1",
            color = Color.White,
            modifier = Modifier
                .padding(start = 24.dp, end = 4.dp)
        )
        AsyncImage(
            model = "https://picsum.photos/id/1005/200/300",
            contentDescription = "",
            modifier = Modifier.size(50.dp).clip(CircleShape)
            )

    }

}



/*@Composable
fun Greeting(name: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "1",
            color = Color.Black,
            modifier = Modifier
                .padding(start = 24.dp, end = 4.dp)
        )
        AsyncImage(
            model = "https://picsum.photos/id/1005/200/300",
            contentDescription = "",
            modifier = Modifier.size(50.dp).clip(CircleShape)
        )


        Text(
            text = "중앙 정렬 텍스트",
            color = Color.Black,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}*/



@Preview @Composable
fun GreetingPreview() {
    Greeting("Android")
}
