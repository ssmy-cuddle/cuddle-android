package com.ssmy.cuddle.ui.main.contents.community.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.ItemDailyPostBinding
import com.ssmy.cuddle.ui.main.contents.community.model.data.CommunityItemData
import com.ssmy.cuddle.ui.main.contents.community.viewmodels.DailyViewModel

class DailyPostAdapter(
    private val viewModel: DailyViewModel,
    private val onCommentClick: (CommunityItemData.Post) -> Unit
) : ListAdapter<CommunityItemData.Post, DailyPostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            ItemDailyPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, viewModel, onCommentClick)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(
        private val binding: ItemDailyPostBinding,
        private val viewModel: DailyViewModel,
        private val onCommentClick: (CommunityItemData.Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: CommunityItemData.Post) {
            binding.post = post

            // Set up the images in the ViewPager2
            val imagesAdapter = PostImagesAdapter(post.images)
            binding.postImagesViewpager.adapter = imagesAdapter

            // Update like button state
            updateLikeButton(viewModel.isPostLiked(post))

            // Handle like button click
            binding.likeButton.setOnClickListener {
                viewModel.toggleLike(post)
                updateLikeButton(viewModel.isPostLiked(post))
            }

            // Handle comment button click
            binding.commentButton.setOnClickListener {
                onCommentClick(post)
            }
        }

        private fun updateLikeButton(isLiked: Boolean) {
            if (isLiked) {
                binding.likeButton.setImageResource(R.drawable.ic_heart_filled)
            } else {
                binding.likeButton.setImageResource(R.drawable.ic_heart_outline)
            }
        }
    }

    // DiffUtil callback to efficiently update the RecyclerView
    class PostDiffCallback : DiffUtil.ItemCallback<CommunityItemData.Post>() {
        override fun areItemsTheSame(
            oldItem: CommunityItemData.Post,
            newItem: CommunityItemData.Post
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CommunityItemData.Post,
            newItem: CommunityItemData.Post
        ): Boolean {
            return oldItem == newItem
        }
    }
}
