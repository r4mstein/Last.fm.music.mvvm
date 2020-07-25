package com.r4mste1n.core_common.models


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    val attr: Attr? = null,
    @SerializedName("track")
    val track: List<Track>? = null
)