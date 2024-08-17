package com.ssmy.cuddle.ui.main.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.databinding.ItemTravelBinding
import com.ssmy.cuddle.ui.main.home.model.data.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class TravelItemAdapter : ListAdapter<HomeItemData.TravelItem, TravelItemAdapter.ViewHolder>(
    TravelDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemTravelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItemData.TravelItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class TravelDiffCallback : DiffUtil.ItemCallback<HomeItemData.TravelItem>() {
        override fun areItemsTheSame(oldItem: HomeItemData.TravelItem, newItem: HomeItemData.TravelItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: HomeItemData.TravelItem, newItem: HomeItemData.TravelItem): Boolean {
            return oldItem == newItem
        }
    }
}
