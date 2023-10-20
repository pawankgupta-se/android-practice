package com.gowittgroup.core

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.gowittgroup.core.di.component.CoreComponent

class MVITemplateApp : Application() {


    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MVITemplateApp).coreComponent
    }

}

fun Activity.coreComponent() = MVITemplateApp.coreComponent(this)
fun Fragment.coreComponent() = MVITemplateApp.coreComponent(requireContext())
