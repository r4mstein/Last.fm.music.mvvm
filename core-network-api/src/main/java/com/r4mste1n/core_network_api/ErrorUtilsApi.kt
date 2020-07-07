package com.r4mste1n.core_network_api

import okhttp3.ResponseBody

/**
 * Created by Alex Shtain on 28.06.2020.
 */
interface ErrorUtilsApi {

    fun parseError(response: ResponseBody?): String?

}