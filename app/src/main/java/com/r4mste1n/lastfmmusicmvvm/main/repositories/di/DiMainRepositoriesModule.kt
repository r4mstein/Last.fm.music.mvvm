package com.r4mste1n.lastfmmusicmvvm.main.repositories.di

import com.r4mste1n.lastfmmusicmvvm.main.di.MainScope
import com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info.ArtistInfoRepository
import com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info.ArtistInfoRepositoryContract
import com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists.TopArtistsRepository
import com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists.TopArtistsRepositoryContract
import dagger.Binds
import dagger.Module

/**
 * Created by Alex Shtain on 24.05.2020.
 */
@Module
@Suppress("unused")
abstract class DiMainRepositoriesModule {

    @MainScope
    @Binds
    abstract fun provideTopArtistsRepository(repository: TopArtistsRepository): TopArtistsRepositoryContract

    @MainScope
    @Binds
    abstract fun provideArtistInfoRepository(repository: ArtistInfoRepository): ArtistInfoRepositoryContract

}