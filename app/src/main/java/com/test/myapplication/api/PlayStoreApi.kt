package com.test.myapplication.api

import com.test.myapplication.model.AppDetails
import com.test.myapplication.model.AppSummary
import com.test.myapplication.model.ReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayStoreApi {

    @GET("app-details")
    suspend fun getAppDetails(@Query("id") appId: String): AppDetails

    @GET("search")
    suspend fun searchApps(@Query("q") query: String): List<AppSummary> // AppSummary의 리스트를 반환

    @GET("reviews")
    suspend fun getReviews(
        @Query("id") appId: String,
        @Query("sort") sortBy: String = "newest", // 'newest', 'rating', 'helpful'
        @Query("count") count: Int = 100
    ): ReviewsResponse
}