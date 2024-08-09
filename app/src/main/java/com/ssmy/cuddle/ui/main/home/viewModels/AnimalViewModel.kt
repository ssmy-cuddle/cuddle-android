package com.ssmy.cuddle.ui.main.home.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssmy.cuddle.R
import com.ssmy.cuddle.ui.main.home.models.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class AnimalViewModel : ViewModel() {

    private val _animalItems = MutableLiveData<List<HomeItemData.AnimalItem>>().apply {
        value = listOf(
            HomeItemData.AnimalItem("동물 제목 1", "사용자 1", R.drawable.sample_image1),
            HomeItemData.AnimalItem("동물 제목 2", "사용자 2", R.drawable.sample_image2)
        )
    }
    val animalItems: LiveData<List<HomeItemData.AnimalItem>> = _animalItems
}