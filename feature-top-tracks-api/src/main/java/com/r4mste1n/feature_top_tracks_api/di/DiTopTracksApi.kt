package com.r4mste1n.feature_top_tracks_api.di

import com.r4mste1n.feature_top_tracks_api.TopTracksStarterApi

/**
 * Created by Alex Shtain on 22.07.2020.
 */
interface DiTopTracksApi {

    fun topTracksStarter(): TopTracksStarterApi

}