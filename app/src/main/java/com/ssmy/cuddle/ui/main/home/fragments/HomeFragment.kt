package com.ssmy.cuddle.ui.main.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.adapters.ViewBindingAdapter.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.FragmentHomeBinding
import com.ssmy.cuddle.ui.main.home.adapters.CuddleOriginalsAdapter
import com.ssmy.cuddle.ui.main.home.adapters.CustomTabAdapter
import com.ssmy.cuddle.ui.main.home.adapters.ViewPagerAdapter
import com.ssmy.cuddle.ui.main.home.viewModels.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var customTabAdapter: CustomTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupViewPagerAndTabs()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = CuddleOriginalsAdapter()
            setPadding(18, 0, 18, 0) // 처음과 마지막 아이템 옆 간격 18dp
            clipToPadding = false
        }

        viewModel.cuddleOriginalItems.observe(viewLifecycleOwner) { items ->
            (binding.recyclerView.adapter as CuddleOriginalsAdapter).submitList(items)
        }
    }

    // TODO :: 컨텐츠 처리 예정
    private fun setupViewPagerAndTabs() {
        val tabTitles = listOf("일상", "여행기", "Cuddle 과 함께하는 동물들")

        customTabAdapter = CustomTabAdapter(tabTitles) { position ->
            binding.viewPager.currentItem = position
        }

        binding.customTabRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = customTabAdapter
            setHasFixedSize(true)
        }

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (customTabAdapter.selectedPosition != position) {
                    val oldPosition = customTabAdapter.selectedPosition
                    customTabAdapter.selectedPosition = position
                    customTabAdapter.notifyItemChanged(oldPosition)
                    customTabAdapter.notifyItemChanged(position)

                    binding.customTabRecyclerView.smoothScrollToPosition(position)
                }
            }
        })

    }
}