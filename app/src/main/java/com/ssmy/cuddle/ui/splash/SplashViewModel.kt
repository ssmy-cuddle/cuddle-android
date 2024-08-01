package com.ssmy.cuddle.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.util.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * doc 주석
 * @author wookjin
 * @since 8/1/24
 **/
class SplashViewModel(application: Application, private val dataStoreManager: DataStoreManager) : AndroidViewModel(application) {

    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean> get() = _navigateToMain

    fun checkLoginStatus() {
        viewModelScope.launch {
            val token = dataStoreManager.getUserPreference<String>(getApplication(), Constants.LOGIN_TOKEN).first()
            _navigateToMain.postValue(!token.isNullOrEmpty())
        }
    }
}