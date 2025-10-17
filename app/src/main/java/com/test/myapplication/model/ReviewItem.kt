package com.test.myapplication.model

import com.google.gson.annotations.SerializedName

data class ReviewItem(

    @SerializedName("reviewId")
    val reviewId: String,

    @SerializedName("userName")
    val userName: String,

    @SerializedName("userImage")
    val userImage: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("thumbsUpCount")
    val thumbsUpCount: Int,

    @SerializedName("at")
    val at: String,

    @SerializedName("replyContent")
    val replyContent: String?,

    @SerializedName("repliedAt")
    val repliedAt: String?,

    @SerializedName("appVersion")
    val appVersion: String,

    @SerializedName("reviewCreatedVersion")
    val reviewCreatedVersion: String

)