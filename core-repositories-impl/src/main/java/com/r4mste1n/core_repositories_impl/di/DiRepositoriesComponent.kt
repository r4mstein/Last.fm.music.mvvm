package com.r4mste1n.core_repositories_impl.di

import com.r4mste1n.core_network_api.ErrorUtilsApi
import com.r4mste1n.core_network_api.HttpClientApi
import com.r4mste1n.core_repositories_api.di.DiRepositoriesApi
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 28.06.2020.
 */
@Component(
    modules = [DiRepositoriesModule::class],
    dependencies = [DiRepositoriesDependencies::class]
)
@Singleton
abstract class DiRepositoriesComponent : DiRepositoriesApi {

    companion object {

        private lateinit var repositoriesComponent: DiRepositoriesComponent

        fun get(dependencies: DiRepositoriesDependencies): DiRepositoriesComponent {

            if (!::repositoriesComponent.isInitialized) {
                synchronized(DiRepositoriesComponent::class.java) {

                    if (!::repositoriesComponent.isInitialized) {
                        repositoriesComponent = DaggerDiRepositoriesComponent.builder()
                            .diRepositoriesDependencies(dependencies)
                            .build()
                    }

                }
            }

            return repositoriesComponent
        }

    }

    @Component(
        dependencies = [
            ErrorUtilsApi::class,
            HttpClientApi::class
        ]
    )
    @Singleton
    interface DiRepositoriesDependenciesComponent : DiRepositoriesDependencies

}