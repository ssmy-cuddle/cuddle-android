package com.ssmy.cuddle.ui.main.contents.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssmy.cuddle.R
import com.ssmy.cuddle.ui.main.contents.home.model.data.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/5/24
 **/
class HomeViewModel : ViewModel() {

    private val _cuddleOriginalItems = MutableLiveData<List<HomeItemData.CuddleOriginalItem>>().apply {
        value = listOf(
            HomeItemData.CuddleOriginalItem("Original Title 1", "Description 1", R.drawable.sample_image1),
            HomeItemData.CuddleOriginalItem("Original Title 2", "Description 2", R.drawable.sample_image2),
            HomeItemData.CuddleOriginalItem("Original Title 3", "Description 3", R.drawable.sample_image1),
            HomeItemData.CuddleOriginalItem("Original Title 4", "Description 4", R.drawable.sample_image2)
        )
    }
    val cuddleOriginalItems: LiveData<List<HomeItemData.CuddleOriginalItem>> = _cuddleOriginalItems

}
