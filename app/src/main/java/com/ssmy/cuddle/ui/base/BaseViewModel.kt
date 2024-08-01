package com.ssmy.cuddle.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * MainViewModel은 MainActivity에서 사용되는 ViewModel로 로그인 상태를 확인하는 로직을 포함합니다.
 *
 * @author wookjin
 * @since 7/31/24
 **/
open class BaseViewModel(application: Application) : AndroidViewModel(application)