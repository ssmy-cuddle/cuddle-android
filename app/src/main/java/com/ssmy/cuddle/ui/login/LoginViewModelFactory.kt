package com.ssmy.cuddle.ui.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssmy.cuddle.data.DataStoreManager

/**
 * doc 주석
 * @author wookjin
 * @since 8/1/24
 **/
class LoginViewModelFactory(
    private val application: Application,
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(application, dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}