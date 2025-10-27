package com.test.myapplication.searchresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.myapplication.searchresult.view.PinchZoom
import com.test.myapplication.searchresult.view.SearchInPut
import com.test.myapplication.searchresult.view.SearchResultScreen
import com.test.myapplication.searchresult.view.SearchScreenShotSlide
import com.test.myapplication.util.SearchHistoryManager
import kotlinx.coroutines.delay


class SearchPageActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val factory = SearchPageViewModelFactory(SearchHistoryManager(this))
            val viewModel = viewModel<SearchPageViewModel>(factory = factory)
            Navigation(viewModel)
        }
    }
}

@Composable
fun Navigation(viewModel: SearchPageViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SearchPageType.Input.route) {
        composable(route = SearchPageType.Input.route) {  SearchInPut(viewModel, navController) }
        composable(route = SearchPageType.Result.route) {  SearchResultScreen(viewModel, navController)  }
        composable(route = SearchPageType.Screen.route) {   SearchScreenShotSlide(viewModel.screenList, viewModel.screenShotIndex)  }
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






