package com.rocksolidapps.cake

import android.app.Application
import com.rocksolidapps.cake.di.appModule
import com.rocksolidapps.cake.di.restModule
import com.rocksolidapps.cake.uithread.ExecutionThread
import com.rocksolidapps.cake.uithread.UiThread
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CakeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        UiThread.init(object : ExecutionThread {
            override fun uiScheduler() = AndroidSchedulers.mainThread()
        })

        startKoin {
            androidContext(this@CakeApp)
            modules(listOf(appModule, restModule))
        }
    }
}