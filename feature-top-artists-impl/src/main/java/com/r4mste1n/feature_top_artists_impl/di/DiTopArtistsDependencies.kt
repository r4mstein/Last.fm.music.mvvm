package com.r4mste1n.feature_top_artists_impl.di

import com.r4mste1n.core_repositories_api.TopArtistsRepoApi

/**
 * Created by Alex Shtain on 29.06.2020.
 */
interface DiTopArtistsDependencies {

    fun topArtistsRepoApi(): TopArtistsRepoApi

}