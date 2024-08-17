package com.ssmy.cuddle.ui.main.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityViewModel : ViewModel() {

    private val _location = MutableLiveData("상도동")
    val location: LiveData<String> = _location

    fun updateLocation(newLocation: String) {
        _location.value = newLocation
    }

    private val _selectedTab = MutableLiveData(0)
    val selectedTab: LiveData<Int> = _selectedTab

    fun selectTab(index: Int) {
        _selectedTab.value = index
    }
}