package com.example.hiltsample.di

import com.example.hiltsample.data.ProductRepository
import com.example.hiltsample.data.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Another way to bind interfaces
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepository: ProductRepositoryImpl
    ): ProductRepository
}
