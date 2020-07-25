package com.r4mste1n.core_common.models.responses

import com.google.gson.annotations.SerializedName
import com.r4mste1n.core_common.models.Artist

data class ArtistInfoResponse(
    @SerializedName("artist")
    val artist: Artist? = null
)