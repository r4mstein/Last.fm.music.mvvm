package com.r4mste1n.core_common.models.responses

import com.google.gson.annotations.SerializedName
import com.r4mste1n.core_common.models.Tracks

/**
 * Created by Alex Shtain on 22.07.2020.
 */
data class TopTracksResponse(
    @SerializedName("tracks")
    val tracks: Tracks? = null
)