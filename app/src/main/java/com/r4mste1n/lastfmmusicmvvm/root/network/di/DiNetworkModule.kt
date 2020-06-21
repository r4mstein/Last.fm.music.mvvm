package com.r4mste1n.lastfmmusicmvvm.root.network.di

import com.r4mste1n.lastfmmusicmvvm.root.di.AppScope
import com.r4mste1n.lastfmmusicmvvm.root.network.RetrofitHelper
import com.r4mste1n.lastfmmusicmvvm.root.network.RetrofitHelperContract
import com.r4mste1n.lastfmmusicmvvm.root.network.api.LastFmApi
import com.r4mste1n.lastfmmusicmvvm.root.network.createService
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Alex Shtain on 23.05.2020.
 */
@Module
class DiNetworkModule {

    @AppScope
    @Provides
    fun provideLastFmApi(retrofitHelper: RetrofitHelperContract): LastFmApi {
        return retrofitHelper
            .getRetrofit(HttpLoggingInterceptor.Level.BODY)
            .createService(LastFmApi::class.java)
    }

    @Module
    companion object {

        @Provides
        fun provideRetrofitHelper(): RetrofitHelperContract = RetrofitHelper()

    }

}