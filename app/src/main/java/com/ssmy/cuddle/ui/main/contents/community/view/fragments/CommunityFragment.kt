package com.ssmy.cuddle.ui.main.contents.community.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.FragmentCommunityBinding
import com.ssmy.cuddle.ui.main.contents.community.view.activitys.PostCreationActivity
import com.ssmy.cuddle.ui.main.contents.community.viewmodels.CommunityViewModel

class CommunityFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBinding
    private val viewModel: CommunityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        setupTabs()
        observeViewModel()
        if (savedInstanceState == null) {
            replaceFragment(DailyFragment())
        }

        setupFloatingActionButton()
    }

    private fun setupFloatingActionButton() {
        binding.fabFlogging.setOnClickListener {
            startActivity(Intent(requireContext(), PostCreationActivity::class.java))
        }
    }

    // todo :: 일상만 오픈
//    private fun setupTabs() {
//        val tabItems = listOf(
//            CommunityItemData.ContentTabItem("일상", R.drawable.ic_daily),
//            CommunityItemData.ContentTabItem("여행기", R.drawable.ic_travel),
//            CommunityItemData.ContentTabItem("산책메이트", R.drawable.ic_mate),
//            CommunityItemData.ContentTabItem("자유게시판", R.drawable.ic_board)
//        )
//
//        val tabAdapter = CommunityContentTabAdapter(tabItems) { position ->
//            viewModel.selectTab(position)
//        }
//        binding.tabRecyclerView.apply {
//            layoutManager = GridLayoutManager(context, 4)
//            adapter = tabAdapter
//            setHasFixedSize(true)
//        }
//    }

    private fun observeViewModel() {
        viewModel.selectedTab.observe(viewLifecycleOwner) { selectedTabIndex ->
            when (selectedTabIndex) {
                0 -> replaceFragment(DailyFragment())
                1 -> replaceFragment(TravelFragment())
                2 -> replaceFragment(MateFragment())
                3 -> replaceFragment(BoardFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.contentFrameLayout, fragment)
            .commitAllowingStateLoss()
    }
}