package com.ssmy.cuddle.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.base.BaseViewModel
import com.ssmy.cuddle.util.Constants.BIO_KEY
import com.ssmy.cuddle.util.Constants.NICKNAME_KEY
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class UserLoginViewModel(
    application: Application,
    private val dataStoreManager: DataStoreManager
) : BaseViewModel(application) {

    private val _isLoginEnabled = MutableLiveData(false)
    val isLoginEnabled: LiveData<Boolean> get() = _isLoginEnabled

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> get() = _userData

    fun updateLoginState(nickname: String, bio: String) {
        _isLoginEnabled.value = nickname.isNotBlank() && bio.isNotBlank()
    }

    fun saveUserData(nickname: String, bio: String) {
        viewModelScope.launch {
            dataStoreManager.putUserPreference(getApplication(), NICKNAME_KEY, nickname)
            dataStoreManager.putUserPreference(getApplication(), BIO_KEY, bio)
        }
    }

    fun loadUserData(): LiveData<UserData> {
        viewModelScope.launch {
            val nicknameFlow =
                dataStoreManager.getUserPreference(getApplication(), NICKNAME_KEY, "")
            val bioFlow = dataStoreManager.getUserPreference(getApplication(), BIO_KEY, "")
            nicknameFlow.combine(bioFlow) { nickname, bio ->
                UserData(nickname ?: "", bio ?: "")
            }.collect {
                _userData.postValue(it)
            }
        }
        return userData
    }
}

data class UserData(val nickname: String, val bio: String)