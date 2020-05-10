package com.r4mste1n.lastfmmusicmvvm.main.top_artists.models


import com.google.gson.annotations.SerializedName
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.models.Artists

data class TopArtistsResponse(
    @SerializedName("artists")
    val artists: Artists? = null
)