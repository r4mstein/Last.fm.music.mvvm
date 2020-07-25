package com.r4mste1n.feature_home_impl.di

import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.feature_home_api.HomeStarterApi
import com.r4mste1n.feature_home_impl.starter.HomeStarterImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Alex Shtain on 29.06.2020.
 */
@Module
@Suppress("unused")
abstract class DiHomeModule {

    @FeatureScope
    @Binds
    abstract fun provideHomeStarter(homeStarter: HomeStarterImpl): HomeStarterApi

}