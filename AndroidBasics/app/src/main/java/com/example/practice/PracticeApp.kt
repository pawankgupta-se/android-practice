package com.example.practice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class PracticeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    fun createChannel(){
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          val  channel = NotificationChannel("running_service", "Running Service", NotificationManager.IMPORTANCE_HIGH)
           val notificationService = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
           notificationService.createNotificationChannel(channel)
        }
    }

}
