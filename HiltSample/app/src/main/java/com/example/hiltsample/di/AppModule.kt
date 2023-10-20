package com.example.hiltsample.di

import android.app.Application
import com.example.hiltsample.data.HomeTranslations
import com.example.hiltsample.data.HomeTranslationsImpl
import com.example.hiltsample.data.UserRepository
import com.example.hiltsample.data.UserRepositoryImpl
import com.example.hiltsample.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Module need to annotate with @Module and @InstallIn
 */

const val HELLO = "hello"
const val GOOD_BYE = "goodbye"
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun getApiService(): ApiService = Retrofit.Builder()
        .baseUrl("http://www.test.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun getUserRepository(apiService: ApiService, homeTranslations: HomeTranslations) : UserRepository {
        return UserRepositoryImpl(apiService, homeTranslations)
    }

    @Provides
    @Singleton
    fun getHomeTranslations(context: Application, @Named(GOOD_BYE) hello: String) : HomeTranslations {
        return HomeTranslationsImpl(context, hello)
    }

    @Provides
    @Singleton
    @Named(HELLO)
    fun getHello() = "Hello"

    @Provides
    @Singleton
    @Named(GOOD_BYE)
    fun getGoodBye() = "Good Bye"
}
