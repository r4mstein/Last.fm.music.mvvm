package com.r4mste1n.lastfmmusicmvvm.root

import android.app.Application
import com.r4mste1n.lastfmmusicmvvm.BuildConfig
import com.r4mste1n.lastfmmusicmvvm.root.di.ObjectGraph
import timber.log.Timber

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialise Timber
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        // initialize di
        ObjectGraph.initAppComponent(this)

    }

}