package com.r4mste1n.feature_artist_info_impl.di

import com.r4mste1n.core_repositories_api.ArtistInfoRepoApi

/**
 * Created by Alex Shtain on 05.07.2020.
 */
interface DiArtistInfoDependencies {

    fun artistInfoRepoApi(): ArtistInfoRepoApi

}