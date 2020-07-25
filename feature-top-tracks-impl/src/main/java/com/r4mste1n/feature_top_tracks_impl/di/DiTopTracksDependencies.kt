package com.r4mste1n.feature_top_tracks_impl.di

import com.r4mste1n.core_repositories_api.TopTracksRepoApi

/**
 * Created by Alex Shtain on 22.07.2020.
 */
interface DiTopTracksDependencies {

    fun topTracksRepoApi(): TopTracksRepoApi

}