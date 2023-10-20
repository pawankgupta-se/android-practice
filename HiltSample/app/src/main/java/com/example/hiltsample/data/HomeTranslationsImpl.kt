package com.example.hiltsample.data

import android.app.Application
import com.example.hiltsample.R

class HomeTranslationsImpl(private val context: Application, private val msg: String):HomeTranslations {
    init {
        println(msg)
    }
    override fun getError(): String  = context.getString(R.string.error)
}
