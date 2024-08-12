package com.ssmy.cuddle.ui.main.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssmy.cuddle.databinding.FragmentTravelBinding
import com.ssmy.cuddle.ui.main.home.adapters.TravelItemAdapter
import com.ssmy.cuddle.ui.main.home.viewModels.TravelViewModel

class TravelFragment : Fragment() {

    private val viewModel: TravelViewModel by viewModels()
    private lateinit var binding: FragmentTravelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTravelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = TravelItemAdapter()

        viewModel.travelItems.observe(viewLifecycleOwner) { items ->
            (binding.recyclerView.adapter as TravelItemAdapter).submitList(items)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }
}
