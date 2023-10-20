package com.gowittgroup.core.di.component

import android.content.Context
import com.gowittgroup.core.di.module.CoreModule
import com.gowittgroup.data.datasources.CharactersDataSource
import com.gowittgroup.data.di.DataModule
import com.gowittgroup.wrapper.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CoreModule::class, NetworkModule::class, DataModule::class ])
@Singleton
interface CoreComponent {
    @Component.Factory interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }
    val characterDataSource: CharactersDataSource
}
