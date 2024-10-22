package com.ssmy.cuddle.ui.main.contents.profile.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.main.contents.profile.model.data.Pet
import com.ssmy.cuddle.ui.main.contents.profile.model.data.UserData
import com.ssmy.cuddle.util.Constants.BIO_KEY
import com.ssmy.cuddle.util.Constants.NICKNAME_KEY
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val application: Application,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> get() = _userData

    fun loadPets() {
        viewModelScope.launch {
            val fetchedPets = fetchPetsFromApi()
            _pets.value = fetchedPets
        }
    }

    private suspend fun fetchPetsFromApi(): List<Pet> {
        return listOf(

        )
    }

    fun loadUserData() {
        viewModelScope.launch {
            val nicknameFlow = dataStoreManager.getUserPreference<String>(application, NICKNAME_KEY)
            val bioFlow = dataStoreManager.getUserPreference<String>(application, BIO_KEY)

            nicknameFlow.combine(bioFlow) { nickname, bio ->
                UserData(nickname ?: "", bio ?: "")
            }.collect { userData ->
                _userData.postValue(userData)
            }
        }
    }
}
