package com.test.myapplication.model

// AppDetails.kt
import com.google.gson.annotations.SerializedName

// 서버가 주는 JSON의 key와 변수 이름을 매칭시킵니다.
data class AppDetails(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String, // 상세 설명

    @SerializedName("summary")
    val summary: String, // 한 줄 요약

    @SerializedName("icon")
    val icon: String, // 아이콘 이미지 URL

    @SerializedName("score")
    val score: Double,

    @SerializedName("installs")
    val installs: String,

    @SerializedName("developer")
    val developer: String, // 개발사 이름

    @SerializedName("genre")
    val genre: String, // 앱 장르

    @SerializedName("released")
    val released: String // 출시일
)