package com.r4mste1n.core_common.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 17.05.2020.
 */
data class Similar(
    @SerializedName("artist")
    val artist: List<Artist>? = null
)