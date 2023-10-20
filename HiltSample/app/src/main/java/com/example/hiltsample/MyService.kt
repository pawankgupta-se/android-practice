package com.example.hiltsample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.hiltsample.data.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * In order to use hilt in android components like activity/service/broadcast_receivers/content_providers class need to be annotated with @AndroidEnterPoint
 *
 * We use constructor injection here so need field injection but it need to be public
 */

@AndroidEntryPoint
class MyService: Service() {

    @Inject
    lateinit var  userRepository: UserRepository

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}
