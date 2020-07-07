package com.r4mste1n.core_common

/**
 * Created by Alex Shtain on 10.12.2019.
 */
sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val message: String?, val code: String? = null) : Result<Nothing>()

    object IsLoading : Result<Nothing>()

}