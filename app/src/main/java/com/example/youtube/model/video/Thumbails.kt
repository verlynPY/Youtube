package com.example.youtube.model.video

import com.google.gson.annotations.SerializedName


data class Thumbnails (
        @SerializedName("default")
    val default: Default? = null,

        @SerializedName("high")
    var high: High? = null,

        @SerializedName("medium")
    var medium: Medium? = null
)
