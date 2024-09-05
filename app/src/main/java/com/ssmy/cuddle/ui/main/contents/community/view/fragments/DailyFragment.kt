package com.ssmy.cuddle.ui.main.contents.community.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssmy.cuddle.databinding.FragmentDailyCommunityBinding
import com.ssmy.cuddle.ui.main.contents.community.model.data.CommunityItemData
import com.ssmy.cuddle.ui.main.contents.community.view.adapters.DailyPostAdapter
import com.ssmy.cuddle.ui.main.contents.community.viewmodels.DailyViewModel

class DailyFragment : Fragment() {

    private lateinit var binding: FragmentDailyCommunityBinding
    private val viewModel: DailyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Observe the posts LiveData and update the RecyclerView when data changes
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            (binding.recyclerView.adapter as DailyPostAdapter).submitList(posts)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = DailyPostAdapter(viewModel) { post ->
            showCommentsBottomSheet(post)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun showCommentsBottomSheet(post: CommunityItemData.Post) {
        // Implement the logic to show the bottom sheet for comments
    }
}
