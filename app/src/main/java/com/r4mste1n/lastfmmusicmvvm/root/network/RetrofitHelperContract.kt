package com.r4mste1n.lastfmmusicmvvm.root.network

import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Alex Shtain on 23.05.2020.
 */
interface RetrofitHelperContract {

    fun <T> createService(_class: Class<T>, level: HttpLoggingInterceptor.Level): T

}