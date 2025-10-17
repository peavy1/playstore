package com.test.myapplication.model

import com.google.gson.annotations.SerializedName
import com.test.myapplication.R

data class AppDetails(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("headerImage")
    val headerImage: String,

    @SerializedName("screenshots")
    val screenshots: List<String>,

    @SerializedName("score")
    val score: Double,

    @SerializedName("ratings")
    val ratings: Int,

    @SerializedName("reviews")
    val reviews: Int,

    @SerializedName("installs")
    val installs: String,

    @SerializedName("minInstalls")
    val minInstalls: Long,

    @SerializedName("realInstalls")
    val realInstalls: Long,

    @SerializedName("developer")
    val developer: String,

    @SerializedName("genre")
    val genre: String,

    @SerializedName("released")
    val released: String?,

    @SerializedName("updated")
    val updated: Long,

    @SerializedName("version")
    val version: String?,

    @SerializedName("contentRating")
    val contentRating: String,

    @SerializedName("adSupported")
    val containsAds: Boolean,

    @SerializedName("offersIAP")
    val offersInAppPurchases: Boolean,

    @SerializedName("histogram")
    val histogram: List<Int>
)