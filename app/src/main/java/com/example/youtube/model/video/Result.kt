package com.example.youtube.model.video

import com.google.gson.annotations.SerializedName


data class Result (
        @SerializedName("etag")
    var etag: String? = null,

        @SerializedName("items")
    var items: List<Item>? = null,

        @SerializedName("kind")
    var kind: String? = null,

        @SerializedName("nextPageToken")
    var nextPageToken: String? = null,

        @SerializedName("pageInfo")
    var pageInfo: PageInfo? = null,

        @SerializedName("regionCode")
    var regionCode: String? = null
)
