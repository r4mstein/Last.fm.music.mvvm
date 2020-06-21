package com.r4mste1n.lastfmmusicmvvm.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.ArtistInfoViewModel
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.TopArtistsViewModel
import com.r4mste1n.lastfmmusicmvvm.root.view_model.ViewModelFactory
import com.r4mste1n.lastfmmusicmvvm.root.view_model.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alex Shtain on 24.05.2020.
 */
@Module
@Suppress("unused")
abstract class DiMainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArtistInfoViewModel::class)
    abstract fun artistInfoViewModel(viewModel: ArtistInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopArtistsViewModel::class)
    abstract fun topArtistsViewModel(viewModel: TopArtistsViewModel): ViewModel

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}