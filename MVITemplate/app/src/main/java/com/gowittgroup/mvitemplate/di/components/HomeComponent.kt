package com.gowittgroup.mvitemplate.di.components

import com.gowittgroup.core.di.annotations.FeatureScope
import com.gowittgroup.core.di.component.CoreComponent
import com.gowittgroup.mvitemplate.di.module.HomeModule
import com.gowittgroup.mvitemplate.home.HomeActivity
import dagger.Component

@Component(dependencies = [CoreComponent::class], modules = [HomeModule::class])
@FeatureScope
interface HomeComponent {
    @Component.Factory
    interface Factory{
        fun create(component: CoreComponent): HomeComponent
    }
    fun inject(homeActivity: HomeActivity)
}
