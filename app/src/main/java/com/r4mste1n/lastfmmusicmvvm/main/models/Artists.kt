package com.r4mste1n.lastfmmusicmvvm.main.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 17.05.2020.
 */
data class Artists(
    @SerializedName("artist")
    val artist: List<Artist>? = null,
    @SerializedName("@attr")
    val attr: Attr? = null
)