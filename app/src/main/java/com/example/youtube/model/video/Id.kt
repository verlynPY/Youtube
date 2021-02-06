package com.example.youtube.model.video

import com.google.gson.annotations.SerializedName

data class Id (
    @SerializedName("videoId")
    var videoId: String? = null,

    @SerializedName("kind")
    var kind: String? = null
)
