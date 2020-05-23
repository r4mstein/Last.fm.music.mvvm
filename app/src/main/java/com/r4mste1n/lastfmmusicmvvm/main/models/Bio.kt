package com.r4mste1n.lastfmmusicmvvm.main.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 17.05.2020.
 */
data class Bio(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("links")
    val links: Links? = null,
    @SerializedName("published")
    val published: String? = null,
    @SerializedName("summary")
    val summary: String? = null
)