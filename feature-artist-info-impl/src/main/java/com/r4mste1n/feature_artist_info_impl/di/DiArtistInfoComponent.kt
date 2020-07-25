package com.r4mste1n.feature_artist_info_impl.di

import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.core_repositories_api.ArtistInfoRepoApi
import com.r4mste1n.feature_artist_info_api.di.DiArtistInfoApi
import com.r4mste1n.feature_artist_info_impl.ArtistInfoFragment
import dagger.Component

/**
 * Created by Alex Shtain on 05.07.2020.
 */
@Component(
    modules = [DiArtistInfoModule::class],
    dependencies = [DiArtistInfoDependencies::class]
)
@FeatureScope
abstract class DiArtistInfoComponent : DiArtistInfoApi {

    companion object {

        private lateinit var artistInfoComponent: DiArtistInfoComponent

        fun initAndGet(dependencies: DiArtistInfoDependencies): DiArtistInfoComponent {

            if (!::artistInfoComponent.isInitialized) {
                synchronized(DiArtistInfoComponent::class.java) {

                    if (!::artistInfoComponent.isInitialized) {
                        artistInfoComponent = DaggerDiArtistInfoComponent.builder()
                            .diArtistInfoDependencies(dependencies)
                            .build()
                    }

                }
            }

            return artistInfoComponent
        }

        fun get(): DiArtistInfoComponent {

            if (!::artistInfoComponent.isInitialized) {
                throw RuntimeException("you must call 'initAndGet()' method")
            }

            return artistInfoComponent
        }

    }

    abstract fun inject(fragment: ArtistInfoFragment)

    @Component(
        dependencies = [ArtistInfoRepoApi::class]
    )
    @FeatureScope
    interface DiArtistInfoDependenciesComponent : DiArtistInfoDependencies
}