package com.example.youtube.model.channel

import com.google.gson.annotations.SerializedName


class High {
    @SerializedName("height")
    var height: Long? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("width")
    var width: Long? = null
}
