package com.r4mste1n.core_repositories_impl.di

import com.r4mste1n.core_repositories_api.ArtistInfoRepoApi
import com.r4mste1n.core_repositories_api.TopArtistsRepoApi
import com.r4mste1n.core_repositories_api.TopTracksRepoApi
import com.r4mste1n.core_repositories_impl.artist_info.ArtistInfoRepoImpl
import com.r4mste1n.core_repositories_impl.repo_mapper.RepoMapperApi
import com.r4mste1n.core_repositories_impl.repo_mapper.RepoMapperImpl
import com.r4mste1n.core_repositories_impl.top_artists.TopArtistsRepoImpl
import com.r4mste1n.core_repositories_impl.top_tracks.TopTracksRepoImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 28.06.2020.
 */
@Module
@Suppress("unused")
abstract class DiRepositoriesModule {

    @Singleton
    @Binds
    abstract fun provideTopArtists(repo: TopArtistsRepoImpl): TopArtistsRepoApi

    @Singleton
    @Binds
    abstract fun provideArtistInfo(repo: ArtistInfoRepoImpl): ArtistInfoRepoApi

    @Singleton
    @Binds
    abstract fun provideTopTracks(repo: TopTracksRepoImpl): TopTracksRepoApi

    @Singleton
    @Binds
    abstract fun provideRepoMapper(mapper: RepoMapperImpl): RepoMapperApi

}