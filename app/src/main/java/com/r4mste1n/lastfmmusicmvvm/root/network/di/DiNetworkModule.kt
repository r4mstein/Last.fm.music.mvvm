package com.r4mste1n.lastfmmusicmvvm.root.network.di

import com.r4mste1n.lastfmmusicmvvm.root.di.AppScope
import com.r4mste1n.lastfmmusicmvvm.root.network.RetrofitHelper
import com.r4mste1n.lastfmmusicmvvm.root.network.RetrofitHelperContract
import com.r4mste1n.lastfmmusicmvvm.root.network.api.LastFmApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Alex Shtain on 23.05.2020.
 */
@Module(includes = [DiNetworkModule.DiNetworkBinds::class])
@Suppress("unused")
class DiNetworkModule {

    @AppScope
    @Provides
    fun provideLastFmApi(retrofitHelper: RetrofitHelper): LastFmApi {
        return retrofitHelper.createService(
            LastFmApi::class.java,
            HttpLoggingInterceptor.Level.BODY
        )
    }

    @Module
    abstract class DiNetworkBinds {

        @AppScope
        @Binds
        abstract fun provideRetrofitHelper(retrofitHelper: RetrofitHelper): RetrofitHelperContract

    }

}