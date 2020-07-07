package com.r4mste1n.core_repositories_impl.di

import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi

/**
 * Created by Alex Shtain on 28.06.2020.
 */
interface DiRepositoriesDependencies {

    fun errorUtilsApi(): ErrorUtilsApi

    fun httpClientApi(): HttpClientApi

}