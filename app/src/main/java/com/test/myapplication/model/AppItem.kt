package com.test.myapplication.model

import com.google.gson.annotations.SerializedName

data class AppItem(
    @SerializedName("title")
    val title: String,

    @SerializedName("app_id")
    val appId: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("rating")
    val rating: String
)