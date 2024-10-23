package com.ssmy.cuddle

import android.app.Application

/**
 * doc 주석
 * @author wookjin
 * @since 7/31/24
 **/
class CuddleApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
//        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}