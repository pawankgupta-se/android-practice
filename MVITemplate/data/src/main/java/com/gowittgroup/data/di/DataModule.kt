package com.gowittgroup.data.di

import com.gowittgroup.data.datasources.CharactersDataSource
import com.gowittgroup.data.datasources.CharactersDataSourceImpl
import com.gowittgroup.wrapper.services.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun providesDataSource(apiService: ApiService): CharactersDataSource = CharactersDataSourceImpl(apiService)
}
