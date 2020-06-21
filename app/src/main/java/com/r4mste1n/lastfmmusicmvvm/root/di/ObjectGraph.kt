package com.r4mste1n.lastfmmusicmvvm.root.di

import android.content.Context
import com.r4mste1n.lastfmmusicmvvm.main.di.DaggerDiMainComponent
import com.r4mste1n.lastfmmusicmvvm.main.di.DiMainComponent

/**
 * Created by Alex Shtain on 23.05.2020.
 */
object ObjectGraph {

    private lateinit var appComponent: DiAppComponent
    private var mainComponent: DiMainComponent? = null

    fun initAppComponent(context: Context) {

        appComponent = DaggerDiAppComponent.builder()
            .setContext(context)
            .buildAppComponent()
    }

    fun getMainComponent(): DiMainComponent {

        return mainComponent ?: DaggerDiMainComponent.builder().diAppComponent(appComponent).build()
    }

    fun destroyMainComponent() {
        mainComponent = null
    }

}