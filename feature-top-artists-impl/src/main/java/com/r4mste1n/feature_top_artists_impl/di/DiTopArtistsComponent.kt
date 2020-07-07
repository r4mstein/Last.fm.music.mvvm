package com.r4mste1n.feature_top_artists_impl.di

import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.core_repositories_api.TopArtistsRepoApi
import com.r4mste1n.feature_top_artists_api.di.DiTopArtistsApi
import com.r4mste1n.feature_top_artists_impl.TopArtistsFragment
import dagger.Component

/**
 * Created by Alex Shtain on 29.06.2020.
 */
@Component(
    modules = [DiTopArtistsModule::class],
    dependencies = [DiTopArtistsDependencies::class]
)
@FeatureScope
abstract class DiTopArtistsComponent : DiTopArtistsApi {

    companion object {

        private lateinit var topArtistsComponent: DiTopArtistsComponent

        fun initAndGet(dependencies: DiTopArtistsDependencies): DiTopArtistsComponent {

            if (!::topArtistsComponent.isInitialized) {
                synchronized(DiTopArtistsComponent::class.java) {

                    if (!::topArtistsComponent.isInitialized) {
                        topArtistsComponent = DaggerDiTopArtistsComponent.builder()
                            .diTopArtistsDependencies(dependencies)
                            .build()
                    }

                }
            }

            return topArtistsComponent
        }

        fun get(): DiTopArtistsComponent {

            if (!::topArtistsComponent.isInitialized) {
                throw RuntimeException("you must call 'initAndGet()' method")
            }

            return topArtistsComponent
        }

    }

    abstract fun inject(fragment: TopArtistsFragment)

    @Component(
        dependencies = [TopArtistsRepoApi::class]
    )
    @FeatureScope
    interface DiTopArtistsDependenciesComponent : DiTopArtistsDependencies

}