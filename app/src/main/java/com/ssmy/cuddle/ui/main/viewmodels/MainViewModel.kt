package com.ssmy.cuddle.ui.main.viewmodels

import android.app.Application
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.base.BaseViewModel

/**
 * MainViewModel은 MainActivity에서 사용되는 ViewModel로 메인 화면 관련 로직을 포함합니다.
 *
 * @author wookjin
 * @since 7/31/24
 **/
class MainViewModel(
    application: Application,
    private val dataStoreManager: DataStoreManager
) : BaseViewModel(application) {

}
