package com.ssmy.cuddle.ui.main.contents.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssmy.cuddle.R
import com.ssmy.cuddle.ui.main.contents.community.model.data.CommunityItemData
import java.util.concurrent.TimeUnit

class DailyViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<CommunityItemData.Post>>()
    val posts: LiveData<List<CommunityItemData.Post>> = _posts

    init {
        // This is where you can load your posts initially
        _posts.value = generateSamplePosts()
    }

    // Example static data generation
    private fun generateSamplePosts(): List<CommunityItemData.Post> {
        return listOf(
            CommunityItemData.Post(
                id = "1",
                profileImageRes = R.drawable.ic_profile,
                nickname = "User1",
                postTime = System.currentTimeMillis() - 60000,
                images = listOf(R.drawable.sample_image1, R.drawable.sample_image2),
                likes = 10,
                comments = 5,
                description = "This is a sample post."
            ),
            CommunityItemData.Post(
                id = "2",
                profileImageRes = R.drawable.ic_profile,
                nickname = "User2",
                postTime = System.currentTimeMillis() - 3600000,
                images = listOf(R.drawable.sample_image2),
                likes = 20,
                comments = 10,
                description = "Another sample post."
            ),
            CommunityItemData.Post(
                id = "1",
                profileImageRes = R.drawable.ic_profile,
                nickname = "User1",
                postTime = System.currentTimeMillis() - 60000,
                images = listOf(R.drawable.sample_image1, R.drawable.sample_image2),
                likes = 10,
                comments = 5,
                description = "This is a sample post."
            ),
            CommunityItemData.Post(
                id = "2",
                profileImageRes = R.drawable.ic_profile,
                nickname = "User2",
                postTime = System.currentTimeMillis() - 3600000,
                images = listOf(R.drawable.sample_image2),
                likes = 20,
                comments = 10,
                description = "Another sample post."
            )
            // Add more sample posts
        )
    }

    // A set to keep track of liked posts
    private val likedPosts = mutableSetOf<String>()

    // Function to initialize or update the list of posts
    fun setPosts(postList: List<CommunityItemData.Post>) {
        _posts.value = postList
    }

    // Function to check if a post is liked
    fun isPostLiked(post: CommunityItemData.Post): Boolean {
        return likedPosts.contains(post.id)
    }

    // Function to toggle the like state of a post
    fun toggleLike(post: CommunityItemData.Post) {
        if (isPostLiked(post)) {
            likedPosts.remove(post.id)
        } else {
            likedPosts.add(post.id)
        }
    }

    // Function to calculate the time difference between the post time and current time
    fun calculateTimeDifference(postTime: Long): String {
        val currentTime = System.currentTimeMillis()
        val diff = currentTime - postTime

        return when {
            diff < TimeUnit.MINUTES.toMillis(1) -> "${TimeUnit.MILLISECONDS.toSeconds(diff)}초 전"
            diff < TimeUnit.HOURS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toMinutes(diff)}분 전"
            diff < TimeUnit.DAYS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toHours(diff)}시간 전"
            else -> "${TimeUnit.MILLISECONDS.toDays(diff)}일 전"
        }
    }
}
