package com.r4mste1n.feature_artist_info_api

/**
 * Created by Alex Shtain on 05.07.2020.
 */
interface ArtistInfoStarterApi {

    fun get(artistName: String): ArtistInfoApi

}