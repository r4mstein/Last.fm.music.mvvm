package com.r4mste1n.feature_top_tracks_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.core_common.base.ViewContract
import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.core_common.view_model.ViewModelFactory
import com.r4mste1n.core_common.view_model.ViewModelKey
import com.r4mste1n.feature_top_tracks_api.TopTracksStarterApi
import com.r4mste1n.feature_top_tracks_impl.TopTracksView
import com.r4mste1n.feature_top_tracks_impl.TopTracksViewModel
import com.r4mste1n.feature_top_tracks_impl.starter.TopTracksStarterImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Alex Shtain on 22.07.2020.
 */
@Module
@Suppress("unused")
abstract class DiTopTracksModule {

    @FeatureScope
    @Binds
    abstract fun provideStarter(starter: TopTracksStarterImpl): TopTracksStarterApi

    @Binds
    @IntoMap
    @ViewModelKey(TopTracksViewModel::class)
    abstract fun topTracksViewModel(viewModel: TopTracksViewModel): ViewModel

    @FeatureScope
    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @FeatureScope
    @Binds
    abstract fun provideTopTracksView(view: TopTracksView): ViewContract

}