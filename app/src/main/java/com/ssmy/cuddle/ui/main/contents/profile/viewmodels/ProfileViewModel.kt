package com.ssmy.cuddle.ui.main.contents.profile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.ui.main.contents.profile.model.data.Pet
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets

    fun loadPets() {
        viewModelScope.launch {
            val fetchedPets = fetchPetsFromApi()
            _pets.value = fetchedPets
        }
    }

    private suspend fun fetchPetsFromApi(): List<Pet> {
        // todo API
        return listOf(
            Pet(1, "슝슝이", "https://example.com/buddy.jpg", 0, "기린", "2024.05.20", "25", true, "365", false),
            Pet(2, "미경이", "https://example.com/luna.jpg", 1, "코끼리", "2024.09.21", "20", true, "200", false)
        )
    }
}
