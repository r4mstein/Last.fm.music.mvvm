package com.r4mste1n.lastfmmusicmvvm.main.di

import com.r4mste1n.lastfmmusicmvvm.main.artist_info.ArtistInfoView
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.TopArtistsView
import com.r4mste1n.lastfmmusicmvvm.root.base.ViewContract
import dagger.Binds
import dagger.Module

/**
 * Created by Alex Shtain on 23.05.2020.
 */
@Module
@Suppress("unused")
abstract class DiMainModule {

    @Binds
    abstract fun provideArtistInfoView(view: ArtistInfoView): ViewContract

    @Binds
    abstract fun provideTopArtistsView(view: TopArtistsView): ViewContract

}