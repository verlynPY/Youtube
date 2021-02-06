package com.example.youtube.model.channel

import com.google.gson.annotations.SerializedName


class Channel {
    @SerializedName("etag")
    var etag: String? = null

    @SerializedName("items")
    var items: List<Item>? = null

    @SerializedName("kind")
    var kind: String? = null

    @SerializedName("pageInfo")
    var pageInfo: PageInfo? = null
}
