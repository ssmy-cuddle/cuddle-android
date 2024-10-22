package com.ssmy.cuddle.ui.main.contents.profile.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.ItemAddPetBinding
import com.ssmy.cuddle.databinding.ItemPetBinding
import com.ssmy.cuddle.ui.main.contents.profile.model.data.Pet
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * doc 주석
 * @author wookjin
 * @since 9/9/24
 **/
class PetAdapter(
    private var pets: List<Pet>,
    private val onEditPet: (Pet) -> Unit,
    private val onAddPet: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_PET = 0
        private const val VIEW_TYPE_ADD_PET = 1

        @BindingAdapter("genderIcon")
        @JvmStatic
        fun ImageView.setGenderIcon(gender: Int) {
            val drawableRes = if (gender == 0) {
                R.drawable.ic_male
            } else {
                R.drawable.ic_female
            }
            setImageResource(drawableRes)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < pets.size) VIEW_TYPE_PET else VIEW_TYPE_ADD_PET
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_PET) {
            val binding = ItemPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PetViewHolder(binding)
        } else {
            val binding = ItemAddPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AddPetViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PetViewHolder) {
            val pet = pets[position]
            holder.bind(pet)
        } else if (holder is AddPetViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return pets.size + 1
    }

    fun updateData(newPets: List<Pet>) {
        pets = newPets
        notifyDataSetChanged()
    }

    inner class PetViewHolder(private val binding: ItemPetBinding) : RecyclerView.ViewHolder(binding.root) {
        private var isFrontVisible = true

        fun bind(pet: Pet) {
            binding.pet = pet

            val daysTogetherTextView = binding.daysTogether
            val daysPassed = calculateDaysPassed(pet.daysTogether)
            daysTogetherTextView.text = "${daysPassed + 1}"

            binding.root.setOnClickListener {
                if (isFrontVisible) {
                    binding.frontLayout.visibility = View.GONE
                    binding.backLayout.visibility = View.VISIBLE
                } else {
                    binding.frontLayout.visibility = View.VISIBLE
                    binding.backLayout.visibility = View.GONE
                }
                isFrontVisible = !isFrontVisible
            }

            binding.editPet.setOnClickListener {
                onEditPet(pet)
            }
        }
    }

    private fun calculateDaysPassed(daysTogether: String): Long {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val startDate = dateFormat.parse(daysTogether)
            val today = System.currentTimeMillis()
            val diffInMillis = today - startDate.time
            TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        } catch (e: Exception) {
            0
        }
    }

    inner class AddPetViewHolder(private val binding: ItemAddPetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.root.setOnClickListener {
                onAddPet()
            }
        }
    }
}
