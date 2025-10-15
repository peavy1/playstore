package com.test.myapplication.model

import com.google.gson.annotations.SerializedName
import com.test.myapplication.R

data class AppDetails(
    @SerializedName("title")
    val title: String, // 앱 제목

    @SerializedName("description")
    val description: String, // 전체 상세 설명

    @SerializedName("summary")
    val summary: String, // 한 줄 요약

    @SerializedName("icon")
    val icon: String, // 아이콘 이미지 URL

    @SerializedName("headerImage")
    val headerImage: String, // 헤더 배너 이미지 URL

    @SerializedName("screenshots")
    val screenshots: List<String>, // 스크린샷 이미지 URL 리스트!

    @SerializedName("score")
    val score: Double, // 평균 평점 (예: 4.5)

    @SerializedName("ratings")
    val ratings: Int, // 평점 개수 (예: 12345)

    @SerializedName("reviews")
    val reviews: Int, // 리뷰 개수 (예: 5432)

    @SerializedName("installs")
    val installs: String, // 설치 수 (문자열, 예: "1,000,000+")

    @SerializedName("minInstalls")
    val minInstalls: Long,

    @SerializedName("realInstalls")
    val realInstalls: Long, // 실제 설치 수 (숫자)

    @SerializedName("developer")
    val developer: String, // 개발사 이름

    @SerializedName("genre")
    val genre: String, // 앱 장르

    @SerializedName("released")
    val released: String?, // 출시일

    @SerializedName("updated")
    val updated: Long, // 마지막 업데이트 타임스탬프 (숫자)

    @SerializedName("version")
    val version: String?, // 앱 버전 (예: "1.2.3")

    @SerializedName("contentRating")
    val contentRating: String, // 연령 등급 (예: "전체이용가")

    @SerializedName("adSupported")  // 광고 포함 여부를 나타내는 Boolean 값
    val containsAds: Boolean,

    @SerializedName("offersIAP")   // 인앱 구매 여부를 나타내는 Boolean 값
    val offersInAppPurchases: Boolean
) {
   /* val purchaseInfoText: String
        get() = buildList {
            if (containsAds) add("광고 포함")
            if (offersInAppPurchases) add("인앱 구매")
        }.joinToString(separator = " • ")*/


    val purchaseInfoText: String
        get() = buildList {
            if (containsAds) add(R.string.containsAds)
            if (offersInAppPurchases) add(R.string.inAppPurchases)
        }.joinToString(separator = " • ")
}