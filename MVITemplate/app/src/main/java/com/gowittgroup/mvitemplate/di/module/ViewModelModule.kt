package com.gowittgroup.mvitemplate.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gowittgroup.commonui.DaggerViewModelFactory
import com.gowittgroup.core.di.annotations.FeatureScope
import com.gowittgroup.commonui.di.annotations.ViewModelKey
import com.gowittgroup.mvitemplate.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @FeatureScope
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @FeatureScope
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}
