package com.ssmy.cuddle.ui.main.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.databinding.ItemContentTabBinding

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class HomeContentTabAdapter(
    private val tabTitles: List<String>,
    private val onTabSelected: (position: Int) -> Unit
) : RecyclerView.Adapter<HomeContentTabAdapter.ViewHolder>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContentTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tabTitles[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = tabTitles.size

    inner class ViewHolder(private val binding: ItemContentTabBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String, isSelected: Boolean) {
            binding.tabText.text = title
            binding.tabText.isSelected = isSelected

            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION && adapterPosition != selectedPosition) {
                    val oldPosition = selectedPosition
                    selectedPosition = adapterPosition
                    notifyItemChanged(oldPosition)
                    notifyItemChanged(selectedPosition)
                    onTabSelected(selectedPosition) // Update ViewPager
                }
            }
        }
    }
}

