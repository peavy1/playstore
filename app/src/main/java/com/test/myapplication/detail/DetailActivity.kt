package com.test.myapplication.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.test.myapplication.R
import com.test.myapplication.model.AppDetails
import com.test.myapplication.util.Constants.EXTRA_APP_ID
import com.test.myapplication.detail.view.AppInfoSection
import com.test.myapplication.detail.view.ReviewListSection
import com.test.myapplication.detail.view.ReviewSection
import com.test.myapplication.detail.view.TopSection
import com.test.myapplication.detail.view.TopSubSection
import com.test.myapplication.model.ReviewItem


class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = DetailViewModel()
            val appId = intent.getStringExtra(EXTRA_APP_ID) ?: ""
            viewModel.appId = appId

            DetailScreen(
                viewModel = viewModel
            )

        }
    }
}


@Composable
fun DetailScreen(
    viewModel: DetailViewModel
) {
    val appData by viewModel.appData.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getData()
    }
    LoadDetailView(details = appData?.appDetails, reviewList = appData?.reviewList ?: emptyList())
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadDetailView(details: AppDetails?, reviewList: List<ReviewItem>) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

    if(details == null) return

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {   }) {
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
                title = {  }
            )
        },
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color.White),
        ) {
            item {
                TopSection(details)
                TopSubSection(details)
                AppInfoSection(details)
                ReviewSection(details)
                ReviewListSection(reviewList)
            }
        }
    }
}