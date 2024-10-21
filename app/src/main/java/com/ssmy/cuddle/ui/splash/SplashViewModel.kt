package com.ssmy.cuddle.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.util.Constants
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

/**
 * doc 주석
 * @author wookjin
 * @since 8/1/24
 **/
class SplashViewModel(application: Application, private val dataStoreManager: DataStoreManager) :
    AndroidViewModel(application) {

    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean> get() = _navigateToMain

    fun checkLoginStatus() {
        viewModelScope.launch {
            val nicknameFlow = dataStoreManager.getUserPreference<String>(getApplication(), Constants.NICKNAME_KEY)
            val bioFlow = dataStoreManager.getUserPreference<String>(getApplication(), Constants.BIO_KEY)

            nicknameFlow.combine(bioFlow) { nickname, bio ->
                !nickname.isNullOrEmpty() && !bio.isNullOrEmpty()
            }.firstOrNull()?.let { isLoggedIn ->
                _navigateToMain.postValue(isLoggedIn)
            }
        }
    }
}