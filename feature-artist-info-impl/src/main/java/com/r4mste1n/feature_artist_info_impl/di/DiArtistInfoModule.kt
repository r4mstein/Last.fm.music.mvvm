package com.r4mste1n.feature_artist_info_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.core_common.base.ViewContract
import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.core_common.view_model.ViewModelFactory
import com.r4mste1n.core_common.view_model.ViewModelKey
import com.r4mste1n.feature_artist_info_api.ArtistInfoStarterApi
import com.r4mste1n.feature_artist_info_impl.ArtistInfoView
import com.r4mste1n.feature_artist_info_impl.ArtistInfoViewModel
import com.r4mste1n.feature_artist_info_impl.starter.ArtistInfoStarterImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alex Shtain on 05.07.2020.
 */
@Module
@Suppress("unused")
abstract class DiArtistInfoModule {

    @FeatureScope
    @Binds
    abstract fun provideStarter(starter: ArtistInfoStarterImpl): ArtistInfoStarterApi

    @Binds
    @IntoMap
    @ViewModelKey(ArtistInfoViewModel::class)
    abstract fun artistInfoViewModel(viewModel: ArtistInfoViewModel): ViewModel

    @FeatureScope
    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @FeatureScope
    @Binds
    abstract fun provideArtistInfoView(view: ArtistInfoView): ViewContract

}