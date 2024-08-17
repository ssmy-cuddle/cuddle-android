package com.ssmy.cuddle.ui.main.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ssmy.cuddle.databinding.FragmentDailyHomeBinding
import com.ssmy.cuddle.ui.main.home.viewmodel.DailyViewModel
import com.ssmy.cuddle.ui.main.home.view.adapters.DailyItemAdapter

class DailyFragment : Fragment() {

    private val viewModel: DailyViewModel by viewModels()
    private lateinit var binding: FragmentDailyHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = DailyItemAdapter()

        viewModel.dailyItems.observe(viewLifecycleOwner) { items ->
            (binding.recyclerView.adapter as DailyItemAdapter).submitList(items)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }
}
