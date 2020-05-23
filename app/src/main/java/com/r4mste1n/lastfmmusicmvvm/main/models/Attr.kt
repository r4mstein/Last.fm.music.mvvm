package com.r4mste1n.lastfmmusicmvvm.main.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 17.05.2020.
 */
data class Attr(
    @SerializedName("page")
    val page: String? = null,
    @SerializedName("perPage")
    val perPage: String? = null,
    @SerializedName("total")
    val total: String? = null,
    @SerializedName("totalPages")
    val totalPages: String? = null
)