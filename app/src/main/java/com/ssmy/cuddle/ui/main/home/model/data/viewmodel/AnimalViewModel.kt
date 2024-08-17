package com.ssmy.cuddle.ui.main.home.model.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssmy.cuddle.R
import com.ssmy.cuddle.ui.main.home.model.data.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class AnimalViewModel : ViewModel() {

    private val _animalItems = MutableLiveData<List<HomeItemData.AnimalItem>>().apply {
        value = listOf(
            HomeItemData.AnimalItem("동물 제목 1", "사용자 1", R.drawable.sample_image1),
            HomeItemData.AnimalItem("동물 제목 2", "사용자 2", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 3", "사용자 3", R.drawable.sample_image1),
            HomeItemData.AnimalItem("동물 제목 4", "사용자 4", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 5", "사용자 5", R.drawable.sample_image1),
            HomeItemData.AnimalItem("동물 제목 6", "사용자 6", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 7", "사용자 7", R.drawable.sample_image1),
            HomeItemData.AnimalItem("동물 제목 8", "사용자 8", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 9", "사용자 9", R.drawable.sample_image1),
            HomeItemData.AnimalItem("동물 제목 10", "사용자 10", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 11", "사용자 11", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 12", "사용자 12", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 13", "사용자 13", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 14", "사용자 14", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 15", "사용자 15", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 16", "사용자 16", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 17", "사용자 17", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 18", "사용자 18", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 19", "사용자 19", R.drawable.sample_image2),
            HomeItemData.AnimalItem("동물 제목 20", "사용자 20", R.drawable.sample_image2)
        )
    }
    val animalItems: LiveData<List<HomeItemData.AnimalItem>> = _animalItems
}