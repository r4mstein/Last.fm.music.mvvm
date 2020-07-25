package com.r4mste1n.core_network_impl.di

import com.r4mste1n.core_network_api.di.DiNetworkApi
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 28.06.2020.
 */
@Component(
    modules = [NetworkModule::class]
)
@Singleton
abstract class DiNetworkComponent : DiNetworkApi {

    companion object {

        private lateinit var coreNetworkComponent: DiNetworkComponent

        fun get(): DiNetworkComponent {

            if (!::coreNetworkComponent.isInitialized) {
                synchronized(DiNetworkComponent::class.java) {

                    if (!::coreNetworkComponent.isInitialized) {
                        coreNetworkComponent = DaggerDiNetworkComponent.builder().build()
                    }

                }
            }

            return coreNetworkComponent
        }

    }

}