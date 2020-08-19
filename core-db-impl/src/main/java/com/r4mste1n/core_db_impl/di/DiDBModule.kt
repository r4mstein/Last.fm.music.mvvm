package com.r4mste1n.core_db_impl.di

import com.r4mste1n.core_db_api.DBManagerApi
import com.r4mste1n.core_db_impl.db_manager.DBManagerImpl
import com.r4mste1n.core_db_impl.db_mapper.DBMapperApi
import com.r4mste1n.core_db_impl.db_mapper.DBMapperImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Alex Shtain on 15.08.2020.
 */
@Module
@Suppress("unused")
abstract class DiDBModule {

    @Singleton
    @Binds
    abstract fun provideDBMapper(mapper: DBMapperImpl): DBMapperApi

    @Singleton
    @Binds
    abstract fun provideDBManager(manager: DBManagerImpl): DBManagerApi

}