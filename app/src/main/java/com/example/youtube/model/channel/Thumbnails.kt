package com.example.youtube.model.channel

import com.google.gson.annotations.SerializedName

class Thumbnails {

    @SerializedName("default")
    var mDefault: Default? = null
    @SerializedName("high")
    var mHigh: High? = null
    @SerializedName("medium")
    var mMedium: Medium? = null
}