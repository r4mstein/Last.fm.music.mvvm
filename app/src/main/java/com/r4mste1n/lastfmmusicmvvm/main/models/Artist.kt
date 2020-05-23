package com.r4mste1n.lastfmmusicmvvm.main.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 17.05.2020.
 */
data class Artist(
    @SerializedName("bio")
    val bio: Bio? = null,
    @SerializedName("image")
    val image: List<Image>? = null,
    @SerializedName("listeners")
    val listeners: String? = null,
    @SerializedName("mbid")
    val mbid: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("ontour")
    val ontour: String? = null,
    @SerializedName("similar")
    val similar: Similar? = null,
    @SerializedName("stats")
    val stats: Stats? = null,
    @SerializedName("streamable")
    val streamable: String? = null,
    @SerializedName("tags")
    val tags: Tags? = null,
    @SerializedName("url")
    val url: String? = null
)