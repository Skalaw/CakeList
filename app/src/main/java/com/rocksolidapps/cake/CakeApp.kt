package com.rocksolidapps.cake

import android.app.Application
import com.rocksolidapps.cake.di.appModule
import com.rocksolidapps.cake.di.restModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CakeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CakeApp)
            modules(listOf(appModule, restModule))
        }
    }
}