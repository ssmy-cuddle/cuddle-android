package com.ssmy.cuddle.ui.main.contents.community.model.data

object CommunityItemData {
    data class ContentTabItem(val title: String, val iconResId: Int)
    data class Post(
        val id: String,
        val profileImageRes: Int,
        val nickname: String,
        val postTime: Long,
        val images: List<Int>,
        val likes: Int,
        val comments: Int,
        val description: String
    )


}