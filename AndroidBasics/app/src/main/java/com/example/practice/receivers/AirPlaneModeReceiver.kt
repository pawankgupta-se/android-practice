package com.example.practice.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

class AirPlaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if(it.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
               val isTurnedOn = Settings.Global.getInt(
                    context?.contentResolver,
                    Settings.Global.AIRPLANE_MODE_ON
                )!=0

                println("Airplane mode enable: $isTurnedOn")
            }
        }

    }
}
