package com.example.hiltsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Without having application class annotated with @HiltAndroidApp hilt will not work.
 */
@HiltAndroidApp
class HiltSampleApp: Application() {
}
