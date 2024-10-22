package com.ssmy.cuddle.ui.main.contents.profile.view.activitys

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ssmy.cuddle.R
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.databinding.ActivityAnimalProfileBinding
import com.ssmy.cuddle.ui.base.BaseActivity
import com.ssmy.cuddle.ui.main.contents.profile.model.AnimalProfileViewModelFactory
import com.ssmy.cuddle.ui.main.contents.profile.model.data.Pet
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.AnimalProfileViewModel
import java.util.Calendar

class AnimalProfileActivity : BaseActivity<AnimalProfileViewModel>() {

    override val viewModel: AnimalProfileViewModel by viewModels {
        AnimalProfileViewModelFactory(application, DataStoreManager)
    }
    private lateinit var binding: ActivityAnimalProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animal_profile)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        val pet = intent.getParcelableExtra<Pet>("pet")
        if (pet != null) {
            viewModel.setPetData(pet)
        } else {
            viewModel.initializeNewPet()
        }

        viewModel.petGender.observe(this) { gender ->
            gender?.let {
                when (it) {
                    0 -> binding.radioGroupGender.check(R.id.radio_male)
                    1 -> binding.radioGroupGender.check(R.id.radio_female)
                }
            }
        }

        viewModel.petIsNeutered.observe(this) { isNeutered ->
            isNeutered?.let {
                if (it) {
                    binding.radioGroupNeutered.check(R.id.radio_neutered_yes)
                } else {
                    binding.radioGroupNeutered.check(R.id.radio_neutered_no)
                }
            }
        }

        binding.birthdayContainer.setOnClickListener {
            hideKeyboard()
            showDatePicker { year, month, day ->
                viewModel.setBirthday(year, month, day)
            }
        }

        binding.daysTogetherContainer.setOnClickListener {
            hideKeyboard()
            showDatePicker { year, month, day ->
                viewModel.setDaysTogether(year, month, day)
            }
        }

        binding.btnSaveAnimalProfile.setOnClickListener {
            hideKeyboard()
            viewModel.savePetData()
        }
    }


    private fun showDatePicker(onDateSet: (year: Int, month: Int, day: Int) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            onDateSet(year, month + 1, dayOfMonth)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun observeViewModel() {
        viewModel.saveComplete.observe(this) { isComplete ->
            if (isComplete) {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}
