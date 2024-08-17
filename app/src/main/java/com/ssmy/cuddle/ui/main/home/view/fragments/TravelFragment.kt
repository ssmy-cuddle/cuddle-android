package com.ssmy.cuddle.ui.main.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssmy.cuddle.databinding.FragmentTravelMainBinding
import com.ssmy.cuddle.ui.main.home.viewmodel.TravelViewModel
import com.ssmy.cuddle.ui.main.home.view.adapters.TravelItemAdapter

class TravelFragment : Fragment() {

    private val viewModel: TravelViewModel by viewModels()
    private lateinit var binding: FragmentTravelMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTravelMainBinding.inflate(inflater, container, false)
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
