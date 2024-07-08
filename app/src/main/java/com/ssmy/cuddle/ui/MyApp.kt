package com.ssmy.cuddle.ui

import android.app.Application
import com.ssmy.cuddle.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * doc 주석
 * @author wookjin
 * @since 7/8/24
 **/
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}