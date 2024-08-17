package com.ssmy.cuddle.ui.main.home.viewmodel

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
class DailyViewModel : ViewModel() {

    private val _dailyItems = MutableLiveData<List<HomeItemData.DailyItem>>().apply {
        value = listOf(
            HomeItemData.DailyItem("일상 제목 1", "사용자 1", R.drawable.sample_image1, false),
            HomeItemData.DailyItem("일상 제목 2", "사용자 2", R.drawable.sample_image2, true),
            HomeItemData.DailyItem("일상 제목 3", "사용자 3", R.drawable.sample_image1, false),
            HomeItemData.DailyItem("일상 제목 4", "사용자 4", R.drawable.sample_image2, true)
        )
    }
    val dailyItems: LiveData<List<HomeItemData.DailyItem>> = _dailyItems

    // 좋아요 상태를 토글하는 메서드
    fun toggleLike(item: HomeItemData.DailyItem) {
        item.isLiked = !item.isLiked
        _dailyItems.value = _dailyItems.value // List 업데이트를 위한 트릭
    }
}