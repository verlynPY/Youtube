package com.example.youtube.model.channel


import com.google.gson.annotations.SerializedName


class Snippet {
    @SerializedName("country")
    var country: String? = null

    @SerializedName("customUrl")
    var customUrl: String? = null

    @SerializedName("defaultLanguage")
    var defaultLanguage: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("localized")
    var localized: Localized? = null

    @SerializedName("publishedAt")
    var publishedAt: String? = null

    @SerializedName("thumbnails")
    var thumbnails: Thumbnails? = null

    @SerializedName("title")
    var title: String? = null
}
