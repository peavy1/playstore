package com.test.myapplication.searchresult

import com.test.myapplication.util.Constants.INPUT
import com.test.myapplication.util.Constants.RESULT
import com.test.myapplication.util.Constants.SCREEN


sealed class SearchPageType(
    val route: String
) {
    object Input: SearchPageType(
        route = INPUT,
    )

    object Result: SearchPageType(
        route = RESULT
    )

    object Screen: SearchPageType(
        route = SCREEN
    )
}

