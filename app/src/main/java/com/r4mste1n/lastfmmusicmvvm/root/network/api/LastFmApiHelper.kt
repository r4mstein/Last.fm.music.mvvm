package com.r4mste1n.lastfmmusicmvvm.root.network.api

import com.r4mste1n.lastfmmusicmvvm.root.network.RetrofitHelper
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

/**
 * Created by Alex Shtain on 18.04.2020.
 */
class LastFmApiHelper @Inject constructor(
    private val retrofitHelper: RetrofitHelper
) {

    val lastFmApi by lazy { createLastFmApi() }

    private fun createLastFmApi() =
        retrofitHelper.createService(
            LastFmApi::class.java,
            HttpLoggingInterceptor.Level.BODY
        )

}