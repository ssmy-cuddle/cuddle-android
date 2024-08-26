package com.ssmy.cuddle.ui.main.contents.community.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.ItemCommunityContentTabBinding
import com.ssmy.cuddle.ui.main.contents.community.model.data.CommunityItemData

class CommunityContentTabAdapter(
    private val tabItems: List<CommunityItemData.ContentTabItem>,
    private val onTabSelected: (position: Int) -> Unit
) : RecyclerView.Adapter<CommunityContentTabAdapter.TabViewHolder>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val binding = ItemCommunityContentTabBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TabViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.bind(tabItems[position], position)
    }

    override fun getItemCount(): Int = tabItems.size

    inner class TabViewHolder(private val binding: ItemCommunityContentTabBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tabItem: CommunityItemData.ContentTabItem, position: Int) {
            binding.tabNameTextView.text = tabItem.title
            binding.tabIconImageView.setImageResource(tabItem.iconResId)

            binding.root.setOnClickListener {
                if (selectedPosition != position) {
                    val oldPosition = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(oldPosition)
                    notifyItemChanged(position)
                    onTabSelected(position)
                }
            }

            binding.underlineView.apply {
                val context = binding.root.context
                if (selectedPosition == position) {
                    setBackgroundColor(context.getColor(R.color.black))
                    layoutParams.height =
                        context.resources.getDimensionPixelSize(R.dimen.selected_tab_line_height)
                } else {
                    setBackgroundColor(context.getColor(R.color.color_848484))
                    layoutParams.height =
                        context.resources.getDimensionPixelSize(R.dimen.unselected_tab_line_height)
                }
                requestLayout()
            }
        }
    }
}