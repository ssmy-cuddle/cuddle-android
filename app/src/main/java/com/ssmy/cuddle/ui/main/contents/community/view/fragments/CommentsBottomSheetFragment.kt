package com.ssmy.cuddle.ui.main.contents.community.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.FragmentCommentsBottomSheetBinding
import com.ssmy.cuddle.ui.main.contents.community.model.data.CommunityItemData

class CommentsBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCommentsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(post: CommunityItemData.Post): CommentsBottomSheetFragment {
            val fragment = CommentsBottomSheetFragment()
            val args = Bundle()
//            args.putSerializable("post", post)
            fragment.arguments = args
            return fragment
        }
    }
}
