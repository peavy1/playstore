package com.test.myapplication.api

import com.test.myapplication.model.AppDetails
import com.test.myapplication.model.AppSummary
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayStoreApi {

    // 기존 상세 정보 요청 함수
    @GET("app-details")
    suspend fun getAppDetails(@Query("id") appId: String): AppDetails

    // 새로 추가한 검색 목록 요청 함수
    @GET("search")
    suspend fun searchApps(@Query("q") query: String): List<AppSummary> // AppSummary의 리스트를 반환

    // 새로 추가한 인기 차트 요청 함수
    @GET("top-charts")
    suspend fun getTopCharts(@Query("chart") chartType: String): List<AppSummary>

    // 새로 추가한 에디터 추천 요청 함수
    @GET("editors-choice")
    suspend fun getEditorsChoice(): List<AppSummary>

    // 새로 추가한 카테고리별 앱 목록 요청 함수
    @GET("apps-by-category")
    suspend fun getAppsByCategory(@Query("category") categoryName: String): List<AppSummary>

    // 새로 추가한 카테고리별 인기 차트 요청 함수
    @GET("top-chart-by-category")
    suspend fun getTopChartByCategory(
        @Query("category") categoryName: String,
        @Query("chart") chartType: String
    ): List<AppSummary>

}