package com.test.myapplication.searchresult

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.myapplication.model.AppSummary
import com.test.myapplication.searchresult.view.SearchInPut
import com.test.myapplication.searchresult.view.SearchResultScreen
import com.test.myapplication.util.Constants.EXTRA_QUERY
import com.test.myapplication.util.SearchHistoryManager
import kotlinx.coroutines.delay


class SearchActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = SearchResultViewModel(SearchHistoryManager(this))
            viewModel.query = intent.getStringExtra(EXTRA_QUERY) ?: ""
            Navigation(viewModel)
        }
    }
}

@Composable
fun Navigation(viewModel: SearchResultViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SearchPageType.Home.route) {
        composable(route = SearchPageType.Home.route) {  SearchInPut(viewModel, navController) }
        composable(route = SearchPageType.Result.route) {  SearchResultScreen(viewModel, navController)  }
    }
}

fun Modifier.autoFocusOnShow(): Modifier = composed {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
    }
    this.focusRequester(focusRequester)
}






