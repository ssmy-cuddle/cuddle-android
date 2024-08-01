package com.ssmy.cuddle.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.base.BaseViewModel
import com.ssmy.cuddle.util.Constants
import kotlinx.coroutines.launch

/**
 * LoginViewModel은 로그인 화면에서 사용되는 ViewModel입니다.
 *
 * @author wookjin
 * @since 7/31/24
 **/
class LoginViewModel(
    application: Application,
    private val dataStoreManager: DataStoreManager
) : BaseViewModel(application) {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login() {
        viewModelScope.launch {
            dataStoreManager.putUserPreference(getApplication(), Constants.LOGIN_TOKEN, "test_token")
            _loginSuccess.postValue(true)
        }
    }
}