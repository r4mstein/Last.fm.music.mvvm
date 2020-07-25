package com.r4mste1n.core_repositories_api.di

import com.r4mste1n.core_repositories_api.ArtistInfoRepoApi
import com.r4mste1n.core_repositories_api.TopArtistsRepoApi

/**
 * Created by Alex Shtain on 28.06.2020.
 */
interface DiRepositoriesApi {

    fun topArtistsRepoApi(): TopArtistsRepoApi

    fun artistInfoRepoApi(): ArtistInfoRepoApi

}