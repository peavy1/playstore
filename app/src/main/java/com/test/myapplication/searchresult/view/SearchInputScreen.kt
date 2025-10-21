package com.test.myapplication.searchresult.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.test.myapplication.R
import com.test.myapplication.searchresult.SearchPageType
import com.test.myapplication.searchresult.SearchResultViewModel
import com.test.myapplication.searchresult.autoFocusOnShow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInPut(
    viewModel: SearchResultViewModel,
    navController: NavHostController
) {
    var searchText by remember { mutableStateOf("") }
    val history by viewModel.searchHistory.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White
                ),
                title = {
                    TextField(
                        value = searchText,
                        textStyle = TextStyle(fontSize = 18.sp),
                        onValueChange = { searchText = it },
                        placeholder = { Text("앱 및 게임 검색") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier.autoFocusOnShow()
                    )
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.query = searchText
                        navController.navigate(SearchPageType.Result.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "검색"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(0.5.dp).background(Color.Black)
            ) {  }

            LazyColumn(

            ) {
                items(history) { query ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(
                                    color = Color.Gray.copy(alpha = 0.1f)
                                ),
                                onClick = {
                                    viewModel.query = query
                                    navController.navigate(SearchPageType.Result.route)
                                }
                            )
                            .padding(vertical = 15.dp, horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_time),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = query,
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.W400,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        Image(
                            painterResource(id = R.drawable.ic_outward),
                            contentDescription = ""
                        )
                    }
                }
            }
        }

    }
}