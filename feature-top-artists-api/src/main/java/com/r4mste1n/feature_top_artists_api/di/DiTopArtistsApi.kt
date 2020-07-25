package com.r4mste1n.feature_top_artists_api.di

import com.r4mste1n.feature_top_artists_api.TopArtistsStarterApi

/**
 * Created by Alex Shtain on 29.06.2020.
 */
interface DiTopArtistsApi {

    fun topArtistsStarter(): TopArtistsStarterApi

}