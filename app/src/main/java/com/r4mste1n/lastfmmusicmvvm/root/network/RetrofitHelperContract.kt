package com.r4mste1n.lastfmmusicmvvm.root.network

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Created by Alex Shtain on 23.05.2020.
 */
interface RetrofitHelperContract {

    fun getRetrofit(level: HttpLoggingInterceptor.Level): Retrofit

}