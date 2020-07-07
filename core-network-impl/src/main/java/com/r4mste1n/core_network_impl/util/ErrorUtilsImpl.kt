package com.r4mste1n.core_network_impl.util

import com.google.gson.JsonParser
import com.r4mste1n.core_network_api.ErrorUtilsApi
import okhttp3.ResponseBody
import javax.inject.Inject


/**
 * Created by Alex Shtain on 18.04.2020.
 */
class ErrorUtilsImpl @Inject constructor() : ErrorUtilsApi {

    override fun parseError(response: ResponseBody?): String? {
        val errorJsonString = response?.string()

        return when {
            errorJsonString.isNullOrEmpty() -> null
            else -> {
                try {
                    JsonParser.parseString(errorJsonString)
                        .asJsonObject["message"]
                        .asString
                } catch (exception: Exception) {
                    null
                }
            }
        }
    }

}
