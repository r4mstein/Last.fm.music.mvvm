package com.r4mste1n.lastfmmusicmvvm.root.network

import retrofit2.Retrofit

/**
 * Created by Alex Shtain on 21.06.2020.
 */
fun <T> Retrofit.createService(_class: Class<T>): T = this.create(_class)