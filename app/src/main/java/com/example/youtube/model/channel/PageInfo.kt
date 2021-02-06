package com.example.youtube.model.channel

import com.google.gson.annotations.SerializedName


class PageInfo {
    @SerializedName("resultsPerPage")
    var resultsPerPage: Long? = null

    @SerializedName("totalResults")
    var totalResults: Long? = null
}
