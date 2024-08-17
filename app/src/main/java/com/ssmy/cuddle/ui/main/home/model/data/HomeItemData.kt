package com.ssmy.cuddle.ui.main.home.model.data

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
object HomeItemData {
    data class CuddleOriginalItem(val title: String, val description: String, val imageResId: Int)
    data class DailyItem(val title: String, val nickname: String, val imageResId: Int, var isLiked: Boolean = false)
    data class TravelItem(val title: String, val nickname: String, val content: String, val imageResId: Int)
    data class AnimalItem(val title: String, val nickname: String, val imageResId: Int)
}
