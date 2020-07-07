package com.r4mste1n.feature_artist_info_api.di

import com.r4mste1n.feature_artist_info_api.ArtistInfoStarterApi

/**
 * Created by Alex Shtain on 05.07.2020.
 */
interface DiArtistInfoApi {

    fun artistInfoStarter(): ArtistInfoStarterApi

}