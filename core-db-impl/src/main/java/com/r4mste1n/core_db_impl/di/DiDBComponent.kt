package com.r4mste1n.core_db_impl.di

import android.content.Context
import com.r4mste1n.core_db_api.di.DiDBApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 15.08.2020.
 */
@Component(
    modules = [DiDBModule::class]
)
@Singleton
abstract class DiDBComponent : DiDBApi {

    companion object {

        private lateinit var dbComponent: DiDBComponent

        fun get(context: Context): DiDBComponent {

            if (!::dbComponent.isInitialized) {
                synchronized(DiDBComponent::class.java) {

                    if (!::dbComponent.isInitialized) {
                        dbComponent = DaggerDiDBComponent.builder()
                            .context(context)
                            .build()
                    }

                }
            }

            return dbComponent
        }

    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): DiDBComponent

    }

}