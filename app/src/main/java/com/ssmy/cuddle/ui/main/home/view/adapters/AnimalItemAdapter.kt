package com.ssmy.cuddle.ui.main.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.databinding.ItemAnimalBinding
import com.ssmy.cuddle.ui.main.home.model.data.HomeItemData

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class AnimalItemAdapter : ListAdapter<HomeItemData.AnimalItem, AnimalItemAdapter.ViewHolder>(
    AnimalDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItemData.AnimalItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class AnimalDiffCallback : DiffUtil.ItemCallback<HomeItemData.AnimalItem>() {
        override fun areItemsTheSame(
            oldItem: HomeItemData.AnimalItem,
            newItem: HomeItemData.AnimalItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: HomeItemData.AnimalItem,
            newItem: HomeItemData.AnimalItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
