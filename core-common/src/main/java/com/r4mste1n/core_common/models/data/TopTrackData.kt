package com.r4mste1n.core_common.models.data

/**
 * Created by Alex Shtain on 16.08.2020.
 */
data class TopTrackData(
    val name: String,
    val singer: String? = null,
    val photoUrl: String? = null,
    val playCount: String? = null,
    val hearersCount: String? = null
)