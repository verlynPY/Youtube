package com.example.youtube.model.video

import com.google.gson.annotations.SerializedName

data class PageInfo (
    @SerializedName("resultsPerPage")
    var resultsPerPage: Long? = null,

    @SerializedName("totalResults")
    var totalResults: Long? = null
)
