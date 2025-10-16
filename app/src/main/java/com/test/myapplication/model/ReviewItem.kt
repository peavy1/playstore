package com.test.myapplication.model

// ReviewItem.kt
import com.google.gson.annotations.SerializedName

data class ReviewItem(
    @SerializedName("userName")
    val userName: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("at") // 리뷰 작성 시간 (타임스탬프)
    val timestamp: Long
)

