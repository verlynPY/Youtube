package com.example.youtube.model.channel

import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("etag")
    var etag: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("kind")
    var kind: String? = null

    @SerializedName("snippet")
    var snippet: Snippet? = null
}
