package com.ssmy.cuddle.ui.main.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssmy.cuddle.databinding.FragmentHomeBinding
import com.ssmy.cuddle.ui.main.home.adapters.ContentTabAdapter
import com.ssmy.cuddle.ui.main.home.adapters.CuddleOriginalsAdapter
import com.ssmy.cuddle.ui.main.home.adapters.ContentViewPagerAdapter
import com.ssmy.cuddle.ui.main.home.viewModels.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

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
        setupContent()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = CuddleOriginalsAdapter()
        }

        viewModel.cuddleOriginalItems.observe(viewLifecycleOwner) { items ->
            (binding.recyclerView.adapter as CuddleOriginalsAdapter).submitList(items)
        }
    }

    private fun setupContent() {
        val tabTitles = listOf("일상", "여행기", "Cuddle 과 함께하는 동물들")
        val contentTabAdapter = ContentTabAdapter(tabTitles) { position ->
            Log.d("HomeFragment", "Tab selected: $position")
            binding.contentViewPager.currentItem = position
        }

        binding.contentTabRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = contentTabAdapter
        }


        val adapter = ContentViewPagerAdapter(this)
        binding.contentViewPager.adapter = adapter

        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d("HomeFragment", "ViewPager page selected: $position")
                if (contentTabAdapter.selectedPosition != position) {
                    val oldPosition = contentTabAdapter.selectedPosition
                    contentTabAdapter.selectedPosition = position
                    contentTabAdapter.notifyItemChanged(oldPosition)
                    contentTabAdapter.notifyItemChanged(position)
                    binding.contentTabRecyclerView.smoothScrollToPosition(position)
                }
            }
        })
    }
}