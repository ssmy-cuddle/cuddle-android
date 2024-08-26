package com.ssmy.cuddle.ui.main.contents.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssmy.cuddle.R
import com.ssmy.cuddle.ui.main.contents.home.model.data.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class TravelViewModel : ViewModel() {

    private val _travelItems = MutableLiveData<List<HomeItemData.TravelItem>>().apply {
        value = listOf(
            HomeItemData.TravelItem("여행기 제목 1", "사용자 1", "여행기 내용 1", R.drawable.sample_image1),
            HomeItemData.TravelItem("여행기 제목 2", "사용자 2", "여행기 내용 2", R.drawable.sample_image2)
        )
    }
    val travelItems: LiveData<List<HomeItemData.TravelItem>> = _travelItems
}