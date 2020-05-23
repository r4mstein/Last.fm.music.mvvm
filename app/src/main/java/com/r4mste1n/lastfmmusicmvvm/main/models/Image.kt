package com.r4mste1n.lastfmmusicmvvm.main.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 17.05.2020.
 */
data class Image(
    @SerializedName("size")
    val size: String? = null,
    @SerializedName("#text")
    val text: String? = null
)