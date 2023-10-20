package com.example.practice.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class TestReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "TEST_ACTION")
        {
            Log.d("TestReceiver", "Received test broadcast")
        }
    }
}
