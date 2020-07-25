package com.r4mste1n.core_common.models


import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("artist")
    val artist: Artist? = null,
    @SerializedName("duration")
    val duration: String? = null,
    @SerializedName("image")
    val image: List<Image>? = null,
    @SerializedName("listeners")
    val listeners: String? = null,
    @SerializedName("mbid")
    val mbid: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("playcount")
    val playcount: String? = null,
    @SerializedName("url")
    val url: String? = null
)