package com.ssmy.cuddle.ui.main.contents.profile.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.AnimalProfileViewModel
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.UserProfileViewModel

/**
 * doc 주석
 * @author wookjin
 * @since 8/1/24
 **/
class AnimalProfileViewModelFactory(
    private val application: Application,
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalProfileViewModel::class.java)) {
            return AnimalProfileViewModel(application, dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}