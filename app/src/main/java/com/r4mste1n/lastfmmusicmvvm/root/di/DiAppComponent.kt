package com.r4mste1n.lastfmmusicmvvm.root.di

import android.content.Context
import com.r4mste1n.lastfmmusicmvvm.root.network.api.LastFmApi
import com.r4mste1n.lastfmmusicmvvm.root.network.di.DiNetworkModule
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Alex Shtain on 23.05.2020.
 */
@AppScope
@Component(
    modules = [
        DiNetworkModule::class
    ]
)
interface DiAppComponent {

    fun context(): Context

    fun getLastFmApi(): LastFmApi

    @Component.Builder
    interface AppComponentBuilder {

        fun buildAppComponent(): DiAppComponent

        @BindsInstance
        fun setContext(context: Context): AppComponentBuilder

    }

}