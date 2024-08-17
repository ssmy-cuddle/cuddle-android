package com.ssmy.cuddle.ui.main.contents.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.databinding.ItemCuddleOriginalBinding
import com.ssmy.cuddle.ui.main.contents.home.model.data.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class CuddleOriginalsAdapter :
    ListAdapter<HomeItemData.CuddleOriginalItem, CuddleOriginalsAdapter.ViewHolder>(
        CuddleOriginalDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCuddleOriginalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemCuddleOriginalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItemData.CuddleOriginalItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class CuddleOriginalDiffCallback : DiffUtil.ItemCallback<HomeItemData.CuddleOriginalItem>() {
        override fun areItemsTheSame(
            oldItem: HomeItemData.CuddleOriginalItem,
            newItem: HomeItemData.CuddleOriginalItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: HomeItemData.CuddleOriginalItem,
            newItem: HomeItemData.CuddleOriginalItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}