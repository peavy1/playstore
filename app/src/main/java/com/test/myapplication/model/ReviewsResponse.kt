package com.test.myapplication.model

import com.google.gson.annotations.SerializedName

// 서버의 전체 응답을 담을 클래스
data class ReviewsResponse(
    @SerializedName("reviews")
    val reviews: List<ReviewItem>,

    @SerializedName("nextToken")
    val nextToken: String?
)