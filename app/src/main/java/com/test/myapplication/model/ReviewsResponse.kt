package com.test.myapplication.model

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("reviews")
    val reviews: List<ReviewItem>,

    @SerializedName("nextToken")
    val nextToken: String?
)