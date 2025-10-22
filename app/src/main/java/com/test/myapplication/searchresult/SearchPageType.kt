package com.test.myapplication.searchresult


sealed class SearchPageType(
    val route: String
) {
    object Home: SearchPageType(
        route = "input",
    )

    object Result: SearchPageType(
        route = "result"
    )

    object Screen: SearchPageType(
        route = "screen"
    )
}

/*
sealed class SearchPageType(
    val route: String
) {
    object Home: SearchPageType(
        route = "input",
    )

    object Result: SearchPageType(
        route = "result"
    )

    object Screen: SearchPageType(
        route = "screen"
    )
}*/
