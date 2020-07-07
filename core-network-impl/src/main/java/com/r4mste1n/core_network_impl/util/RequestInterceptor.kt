package com.r4mste1n.core_network_impl.util

import com.r4mste1n.core_network_impl.util.NetworkConstants.API_KEY
import com.r4mste1n.core_network_impl.util.NetworkConstants.RESPONSE_FORMAT
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * Created by Alex Shtain on 18.04.2020.
 */
class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("format", RESPONSE_FORMAT)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(url)
                .build()
        )
    }

}