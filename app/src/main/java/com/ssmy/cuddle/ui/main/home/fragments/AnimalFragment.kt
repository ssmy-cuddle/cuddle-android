package com.ssmy.cuddle.ui.main.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.databinding.FragmentAnimalBinding
import com.ssmy.cuddle.ui.main.home.adapters.AnimalItemAdapter
import com.ssmy.cuddle.ui.main.home.viewmodel.AnimalViewModel

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
        val gridLayoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = AnimalItemAdapter()

        viewModel.animalItems.observe(viewLifecycleOwner) { items ->
            (binding.recyclerView.adapter as AnimalItemAdapter).submitList(items)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }
}
