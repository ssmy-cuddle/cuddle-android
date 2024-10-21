package com.ssmy.cuddle.ui.main.contents.profile.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.databinding.FragmentProfileBinding
import com.ssmy.cuddle.ui.main.contents.profile.model.data.Pet
import com.ssmy.cuddle.ui.main.contents.profile.model.data.ProfileViewModelFactory
import com.ssmy.cuddle.ui.main.contents.profile.view.activitys.UserProfileActivity
import com.ssmy.cuddle.ui.main.contents.profile.view.adapters.PetAdapter
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var petAdapter: PetAdapter
    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(requireActivity().application, DataStoreManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        petAdapter = PetAdapter(emptyList(), ::onEditPet, ::onAddPet)
        binding.petsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = petAdapter
        }

        profileViewModel.pets.observe(viewLifecycleOwner) { pets ->
            petAdapter = PetAdapter(pets, ::onEditPet, ::onAddPet)
            binding.petsRecyclerView.adapter = petAdapter
        }

        profileViewModel.loadPets()

        binding.editProfileButton.setOnClickListener {
            startActivity(Intent(requireContext(), UserProfileActivity::class.java))
        }


        profileViewModel.userData.observe(this) { userData ->
            binding.textNickname.text = userData.nickname
            binding.textBio.text = userData.bio
        }

        profileViewModel.loadUserData()
    }

    private fun onEditPet(pet: Pet) {
    }

    private fun onAddPet() {
    }
}
