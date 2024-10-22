package com.ssmy.cuddle.ui.main.contents.profile.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.main.contents.profile.model.data.Pet
import com.ssmy.cuddle.ui.main.contents.profile.model.data.UserData
import com.ssmy.cuddle.util.Constants.BIO_KEY
import com.ssmy.cuddle.util.Constants.CURRENT_PET_ID_KEY
import com.ssmy.cuddle.util.Constants.NICKNAME_KEY
import com.ssmy.cuddle.util.Constants.PET_BIRTHDAY_KEY
import com.ssmy.cuddle.util.Constants.PET_BREED_KEY
import com.ssmy.cuddle.util.Constants.PET_DAYS_TOGETHER_KEY
import com.ssmy.cuddle.util.Constants.PET_GENDER_KEY
import com.ssmy.cuddle.util.Constants.PET_IS_NEUTERED_KEY
import com.ssmy.cuddle.util.Constants.PET_NAME_KEY
import com.ssmy.cuddle.util.Constants.PET_WEIGHT_KEY
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
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
            val fetchedPets = fetchPetsFromDataStore()
            _pets.value = fetchedPets
        }
    }

    private suspend fun fetchPetsFromDataStore(): List<Pet> {
        val currentId = getCurrentPetId()
        val pets = mutableListOf<Pet>()

        for (id in 1..currentId) {
            val idString = id.toString()

            val nameFlow = dataStoreManager.getUserPreference(application, "${PET_NAME_KEY}_$idString", "")
            val genderFlow = dataStoreManager.getUserPreference(application, "${PET_GENDER_KEY}_$idString", 0)
            val breedFlow = dataStoreManager.getUserPreference(application, "${PET_BREED_KEY}_$idString", "")
            val birthdayFlow = dataStoreManager.getUserPreference(application, "${PET_BIRTHDAY_KEY}_$idString", "")
            val weightFlow = dataStoreManager.getUserPreference(application, "${PET_WEIGHT_KEY}_$idString", "")
            val isNeuteredFlow = dataStoreManager.getUserPreference(application, "${PET_IS_NEUTERED_KEY}_$idString", false)
            val daysTogetherFlow = dataStoreManager.getUserPreference(application, "${PET_DAYS_TOGETHER_KEY}_$idString", "")

            val name = nameFlow.first() ?: ""
            val gender = genderFlow.first() ?: 0
            val breed = breedFlow.first() ?: ""
            val birthday = birthdayFlow.first() ?: ""
            val weight = weightFlow.first() ?: ""
            val isNeutered = isNeuteredFlow.first() ?: false
            val daysTogether = daysTogetherFlow.first() ?: ""

            if (name.isNotEmpty()) {
                val pet = Pet(
                    id = id,
                    name = name,
                    gender = gender,
                    breed = breed,
                    birthday = birthday,
                    weight = weight,
                    isNeutered = isNeutered,
                    daysTogether = daysTogether
                )

                pets.add(pet)
            }
        }

        return pets
    }

    private suspend fun getCurrentPetId(): Int {
        val currentIdFlow = dataStoreManager.getUserPreference(application, CURRENT_PET_ID_KEY, 0)
        Log.d(">>4>>>", currentIdFlow.first().toString())
        return currentIdFlow.first()!!
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
