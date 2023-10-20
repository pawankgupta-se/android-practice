package com.gowittgroup.commonui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gowittgroup.commonui.AppRouter
import javax.inject.Inject

open class RootBaseActivity: AppCompatActivity() {

    @Inject
    lateinit var appRouter: AppRouter


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

}
