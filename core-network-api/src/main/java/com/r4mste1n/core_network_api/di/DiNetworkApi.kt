package com.r4mste1n.core_network_api.di

import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi

/**
 * Created by Alex Shtain on 21.06.2020.
 */
interface DiNetworkApi {

    fun httpClientApi(): HttpClientApi

    fun errorUtilsApi(): ErrorUtilsApi

}