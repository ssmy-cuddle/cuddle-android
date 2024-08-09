package com.ssmy.cuddle.ui.main.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ssmy.cuddle.databinding.FragmentAnimalBinding
import com.ssmy.cuddle.ui.main.home.adapters.AnimalItemAdapter
import com.ssmy.cuddle.ui.main.home.viewModels.AnimalViewModel

class AnimalFragment : Fragment() {

    private val viewModel: AnimalViewModel by viewModels()
    private lateinit var binding: FragmentAnimalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = AnimalItemAdapter() // AnimalItemAdapter 구현 필요

        viewModel.animalItems.observe(viewLifecycleOwner) { items ->
            (binding.recyclerView.adapter as AnimalItemAdapter).submitList(items)
        }
    }
}
