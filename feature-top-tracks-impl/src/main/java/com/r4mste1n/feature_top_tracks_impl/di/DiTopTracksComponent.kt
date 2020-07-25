package com.r4mste1n.feature_top_tracks_impl.di

import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.core_repositories_api.TopTracksRepoApi
import com.r4mste1n.feature_top_tracks_api.di.DiTopTracksApi
import com.r4mste1n.feature_top_tracks_impl.TopTracksFragment
import dagger.Component

/**
 * Created by Alex Shtain on 22.07.2020.
 */
@Component(
    modules = [DiTopTracksModule::class],
    dependencies = [DiTopTracksDependencies::class]
)
@FeatureScope
abstract class DiTopTracksComponent : DiTopTracksApi {

    companion object {

        private lateinit var topTracksComponent: DiTopTracksComponent

        fun initAndGet(dependencies: DiTopTracksDependencies): DiTopTracksComponent {

            if (!::topTracksComponent.isInitialized) {
                synchronized(DiTopTracksComponent::class.java) {

                    if (!::topTracksComponent.isInitialized) {
                        topTracksComponent = DaggerDiTopTracksComponent.builder()
                            .diTopTracksDependencies(dependencies)
                            .build()
                    }

                }
            }

            return topTracksComponent
        }

        fun get(): DiTopTracksComponent {

            if (!::topTracksComponent.isInitialized) {
                throw RuntimeException("you must call 'initAndGet()' method")
            }

            return topTracksComponent
        }

    }

    abstract fun inject(fragment: TopTracksFragment)

    @Component(
        dependencies = [TopTracksRepoApi::class]
    )
    @FeatureScope
    interface DiTopTracksDependenciesComponent : DiTopTracksDependencies

}