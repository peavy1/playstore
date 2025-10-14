package com.test.myapplication.model


import com.google.gson.annotations.SerializedName

data class AppSummary(
    @SerializedName("appId")
    val appId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("score")
    val score: Double
)