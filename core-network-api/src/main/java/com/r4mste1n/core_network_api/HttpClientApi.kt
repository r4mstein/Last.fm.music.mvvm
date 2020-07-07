package com.r4mste1n.core_network_api

/**
 * Created by Alex Shtain on 21.06.2020.
 */
interface HttpClientApi {

    fun <Api> getApi(
        _class: Class<Api>
    ): Api

}