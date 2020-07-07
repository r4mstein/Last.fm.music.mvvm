package com.r4mste1n.core_network_impl

import com.google.gson.GsonBuilder
import com.r4mste1n.core_network_api.HttpClientApi
import com.r4mste1n.core_network_impl.util.NetworkConstants
import com.r4mste1n.core_network_impl.util.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Alex Shtain on 28.06.2020.
 */
class HttpClientImpl @Inject constructor() : HttpClientApi {

    override fun <Api> getApi(
        _class: Class<Api>
    ): Api {

        return getRetrofit()
            .create(_class)
    }

    private fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(getOkHttpBuilder().build())
            .baseUrl(NetworkConstants.BASE_URL + NetworkConstants.API_VERSION)
            .build()

    private fun getOkHttpBuilder() = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(RequestInterceptor())

}