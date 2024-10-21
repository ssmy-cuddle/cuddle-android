package com.ssmy.cuddle.ui.main.contents.profile.model.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.ProfileViewModel

class ProfileViewModelFactory(
    private val application: Application,
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(application, dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}