package com.ssmy.cuddle.ui.main.contents.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.databinding.ItemDailyBinding
import com.ssmy.cuddle.ui.main.contents.home.model.data.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class DailyItemAdapter : ListAdapter<HomeItemData.DailyItem, DailyItemAdapter.ViewHolder>(
    DailyDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItemData.DailyItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class DailyDiffCallback : DiffUtil.ItemCallback<HomeItemData.DailyItem>() {
        override fun areItemsTheSame(
            oldItem: HomeItemData.DailyItem,
            newItem: HomeItemData.DailyItem
        ): Boolean {
            return oldItem.title == newItem.title // 기본 비교 기준
        }

        override fun areContentsTheSame(
            oldItem: HomeItemData.DailyItem,
            newItem: HomeItemData.DailyItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
