package com.ssmy.cuddle

import android.app.Application
import com.ssmy.cuddle.util.SharedPreferencesManager

/**
 * doc 주석
 * @author wookjin
 * @since 7/11/24
 **/
class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

//        SharedPreferencesManager.init(this)
    }
}