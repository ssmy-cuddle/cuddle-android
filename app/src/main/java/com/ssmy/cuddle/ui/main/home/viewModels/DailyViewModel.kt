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
class DailyViewModel : ViewModel() {

    private val _dailyItems = MutableLiveData<List<HomeItemData.DailyItem>>().apply {
        value = listOf(
            HomeItemData.DailyItem("Daily Title 1", "User 1", R.drawable.sample_image1, false),
            HomeItemData.DailyItem("Daily Title 2", "User 2", R.drawable.sample_image2, true)
        )
    }
    val dailyItems: LiveData<List<HomeItemData.DailyItem>> = _dailyItems

    // 좋아요 상태를 토글하는 메서드
    fun toggleLike(item: HomeItemData.DailyItem) {
        item.isLiked = !item.isLiked
        _dailyItems.value = _dailyItems.value // List 업데이트를 위한 트릭
    }
}