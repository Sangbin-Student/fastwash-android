package com.mooooong.fastwash.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FastWashApplication: Application() {
    companion object {
        lateinit var instance: FastWashApplication
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}