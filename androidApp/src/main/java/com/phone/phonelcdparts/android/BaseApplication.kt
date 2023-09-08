package com.phone.phonelcdparts.android

import android.app.Application
import com.phone.phonelcdparts.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}