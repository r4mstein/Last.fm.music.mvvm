package com.r4mste1n.core_network_impl.di

import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi
import com.r4mste1n.core_network_impl.HttpClientImpl
import com.r4mste1n.core_network_impl.util.ErrorUtilsImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 28.06.2020.
 */
@Module
@Suppress("unused")
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun provideHttpClientApi(httpClient: HttpClientImpl): HttpClientApi

    @Singleton
    @Binds
    abstract fun provideErrorUtils(errorUtils: ErrorUtilsImpl): ErrorUtilsApi

}