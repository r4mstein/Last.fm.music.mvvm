package com.r4mste1n.feature_top_artists_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.core_common.base.ViewContract
import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.core_common.view_model.ViewModelFactory
import com.r4mste1n.core_common.view_model.ViewModelKey
import com.r4mste1n.feature_top_artists_api.TopArtistsStarterApi
import com.r4mste1n.feature_top_artists_impl.TopArtistsView
import com.r4mste1n.feature_top_artists_impl.TopArtistsViewModel
import com.r4mste1n.feature_top_artists_impl.starter.TopArtistsStarterImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alex Shtain on 29.06.2020.
 */
@Module
@Suppress("unused")
abstract class DiTopArtistsModule {

    @FeatureScope
    @Binds
    abstract fun provideStarter(starter: TopArtistsStarterImpl): TopArtistsStarterApi

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(TopArtistsViewModel::class)
    abstract fun topArtistsViewModel(viewModel: TopArtistsViewModel): ViewModel

    @FeatureScope
    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @FeatureScope
    @Binds
    abstract fun provideTopArtistsView(view: TopArtistsView): ViewContract

}