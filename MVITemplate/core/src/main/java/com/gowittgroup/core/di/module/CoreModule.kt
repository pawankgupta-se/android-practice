package com.gowittgroup.core.di.module

import android.app.Application
import com.gowittgroup.core.MVITemplateApp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: MVITemplateApp): Application
}
