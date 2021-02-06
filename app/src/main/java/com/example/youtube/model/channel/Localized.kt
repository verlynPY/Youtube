package com.example.youtube.model.channel

import com.google.gson.annotations.SerializedName


class Localized {
    @SerializedName("description")
    var description: String? = null

    @SerializedName("title")
    var title: String? = null
}