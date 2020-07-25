package com.r4mste1n.core_common.models.responses

import com.google.gson.annotations.SerializedName
import com.r4mste1n.core_common.models.Artists

data class TopArtistsResponse(
    @SerializedName("artists")
    val artists: Artists? = null
)