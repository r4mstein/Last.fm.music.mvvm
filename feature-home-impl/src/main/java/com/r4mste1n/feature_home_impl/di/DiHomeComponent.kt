package com.r4mste1n.feature_home_impl.di

import com.r4mste1n.core_common.di.FeatureScope
import com.r4mste1n.core_navigator_api.NavigatorApi
import com.r4mste1n.feature_home_api.di.DiHomeApi
import com.r4mste1n.feature_home_impl.HomeActivity
import dagger.Component

/**
 * Created by Alex Shtain on 29.06.2020.
 */
@Component(
    modules = [DiHomeModule::class],
    dependencies = [DiHomeDependencies::class]
)
@FeatureScope
abstract class DiHomeComponent : DiHomeApi {

    companion object {

        private lateinit var homeComponent: DiHomeComponent

        fun initAndGet(dependencies: DiHomeDependencies): DiHomeComponent {

            if (!::homeComponent.isInitialized) {
                synchronized(DiHomeComponent::class.java) {

                    if (!::homeComponent.isInitialized) {
                        homeComponent = DaggerDiHomeComponent.builder()
                            .diHomeDependencies(dependencies)
                            .build()
                    }

                }
            }

            return homeComponent
        }

        fun get(): DiHomeComponent {

            if (!::homeComponent.isInitialized) {
                throw RuntimeException("you must call 'initAndGet()' method")
            }

            return homeComponent
        }

    }

    abstract fun inject(activity: HomeActivity)

    @Component(
        dependencies = [
            NavigatorApi::class
        ]
    )
    @FeatureScope
    interface DiHomeDependenciesComponent : DiHomeDependencies

}