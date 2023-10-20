package com.example.practice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

/**
 * Foreground Service
 * 1. Need to create Custom Service class by extending Service
 * 2. Override onBind and onStartCommand methods
 * 3. Need to create notification to run foreground service
 * 4. Notification need channel after Android - O
 * 5. Need to create channel in Application class
 * 6. Declare service in Manifest
 * 7. Declare below two permissions in Manifest
 *  <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
 *  <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
 *  8. After Android - T POST_NOTIFICATIONS permission need to ask at run-time.
 *
 */

class RunningService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_service")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Running Service")
            .setContentInfo("Service is running 5:00")
            .build()

        startForeground(1, notification)
    }


    enum class Actions {
        START, STOP
    }
}
