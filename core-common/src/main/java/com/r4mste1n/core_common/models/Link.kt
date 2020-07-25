package com.r4mste1n.core_common.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 17.05.2020.
 */
data class Link(
    @SerializedName("href")
    val href: String? = null,
    @SerializedName("rel")
    val rel: String? = null,
    @SerializedName("#text")
    val text: String? = null
)