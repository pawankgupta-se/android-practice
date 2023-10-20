package com.gowittgroup.mvitemplate.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gowittgroup.commonui.DaggerViewModelFactory
import com.gowittgroup.core.di.annotations.FeatureScope
import com.gowittgroup.core.di.annotations.ViewModelKey
import com.gowittgroup.core.di.component.CoreComponent
import com.gowittgroup.mvitemplate.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {
    @Binds
    @FeatureScope
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel) : ViewModel

}
