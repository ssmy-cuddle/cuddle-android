package com.ssmy.cuddle.ui.main.contents.profile.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.base.BaseViewModel
import com.ssmy.cuddle.ui.main.contents.profile.model.data.Pet
import com.ssmy.cuddle.util.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * doc 주석
 * @author wookjin
 * @since 9/10/24
 **/
class AnimalProfileViewModel(
    application: Application,
    private val dataStoreManager: DataStoreManager
) : BaseViewModel(application) {

    private val _saveComplete = MutableLiveData<Boolean>()
    val saveComplete: LiveData<Boolean> get() = _saveComplete

    val isPetDataAvailable = MutableLiveData(false)

    val petName = MutableLiveData("")
    val petBreed = MutableLiveData("")
    val petWeight = MutableLiveData("")
    val petGender = MutableLiveData<Int?>()
    val petIsNeutered = MutableLiveData<Boolean?>()
    val birthdayYear = MutableLiveData("")
    val birthdayMonth = MutableLiveData("")
    val birthdayDay = MutableLiveData("")
    val daysTogetherYear = MutableLiveData("")
    val daysTogetherMonth = MutableLiveData("")
    val daysTogetherDay = MutableLiveData("")

    val isSaveButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(petName) { validateForm() }
        addSource(petBreed) { validateForm() }
        addSource(petWeight) { validateForm() }
        addSource(petGender) { validateForm() }
        addSource(petIsNeutered) { validateForm() }
        addSource(birthdayYear) { validateForm() }
        addSource(birthdayMonth) { validateForm() }
        addSource(birthdayDay) { validateForm() }
        addSource(daysTogetherYear) { validateForm() }
        addSource(daysTogetherMonth) { validateForm() }
        addSource(daysTogetherDay) { validateForm() }
    }

    init {
        initializeNewPet()
    }

    fun setGender(gender: Int) {
        petGender.value = gender
    }

    fun setNeutered(isNeutered: Boolean) {
        petIsNeutered.value = isNeutered
    }

    fun setPetData(pet: Pet) {
        isPetDataAvailable.value = true

        petName.value = pet.name
        petBreed.value = pet.breed
        petGender.value = pet.gender
        petWeight.value = pet.weight
        petIsNeutered.value = pet.isNeutered

        val birthdayParts = pet.birthday.split("-")
        if (birthdayParts.size == 3) {
            birthdayYear.value = birthdayParts[0]
            birthdayMonth.value = birthdayParts[1]
            birthdayDay.value = birthdayParts[2]
        }

        val daysTogetherParts = pet.daysTogether.split("-")
        if (daysTogetherParts.size == 3) {
            daysTogetherYear.value = daysTogetherParts[0]
            daysTogetherMonth.value = daysTogetherParts[1]
            daysTogetherDay.value = daysTogetherParts[2]
        }
    }

    fun initializeNewPet() {
        isPetDataAvailable.value = false

        val today = getCurrentDateParts()
        petName.value = ""
        petBreed.value = ""
        petGender.value = null
        petWeight.value = ""
        petIsNeutered.value = null

        birthdayYear.value = today[0]
        birthdayMonth.value = today[1]
        birthdayDay.value = today[2]
        daysTogetherYear.value = today[0]
        daysTogetherMonth.value = today[1]
        daysTogetherDay.value = today[2]
    }

    fun setBirthday(year: Int, month: Int, day: Int) {
        birthdayYear.value = year.toString()
        birthdayMonth.value = String.format("%02d", month)
        birthdayDay.value = String.format("%02d", day)
    }

    fun setDaysTogether(year: Int, month: Int, day: Int) {
        daysTogetherYear.value = year.toString()
        daysTogetherMonth.value = String.format("%02d", month)
        daysTogetherDay.value = String.format("%02d", day)
    }

    private fun validateForm() {
        val isBirthdaySet = !birthdayYear.value.isNullOrEmpty() &&
                !birthdayMonth.value.isNullOrEmpty() &&
                !birthdayDay.value.isNullOrEmpty()

        val isDaysTogetherSet = !daysTogetherYear.value.isNullOrEmpty() &&
                !daysTogetherMonth.value.isNullOrEmpty() &&
                !daysTogetherDay.value.isNullOrEmpty()

        isSaveButtonEnabled.value = !petName.value.isNullOrEmpty() &&
                !petBreed.value.isNullOrEmpty() &&
                !petWeight.value.isNullOrEmpty() &&
                petGender.value != null &&
                petIsNeutered.value != null &&
                isBirthdaySet &&
                isDaysTogetherSet
    }

    fun savePetData() {
        viewModelScope.launch {
            val newId = generateNewPetId()

            val pet = Pet(
                id = newId,
                name = petName.value ?: "",
                gender = petGender.value ?: 0,
                breed = petBreed.value ?: "",
                birthday = "${birthdayYear.value}-${birthdayMonth.value}-${birthdayDay.value}",
                weight = petWeight.value ?: "",
                isNeutered = petIsNeutered.value ?: false,
                daysTogether = "${daysTogetherYear.value}-${daysTogetherMonth.value}-${daysTogetherDay.value}"
            )

            val idString = pet.id.toString()
            dataStoreManager.putUserPreference(getApplication(), "${Constants.PET_NAME_KEY}_$idString", pet.name)
            dataStoreManager.putUserPreference(getApplication(), "${Constants.PET_GENDER_KEY}_$idString", pet.gender)
            dataStoreManager.putUserPreference(getApplication(), "${Constants.PET_BREED_KEY}_$idString", pet.breed)
            dataStoreManager.putUserPreference(getApplication(), "${Constants.PET_BIRTHDAY_KEY}_$idString", pet.birthday)
            dataStoreManager.putUserPreference(getApplication(), "${Constants.PET_WEIGHT_KEY}_$idString", pet.weight)
            dataStoreManager.putUserPreference(getApplication(), "${Constants.PET_IS_NEUTERED_KEY}_$idString", pet.isNeutered)
            dataStoreManager.putUserPreference(getApplication(), "${Constants.PET_DAYS_TOGETHER_KEY}_$idString", pet.daysTogether)

            _saveComplete.postValue(true)
        }
    }

    private suspend fun generateNewPetId(): Int {
        val currentIdFlow = dataStoreManager.getUserPreference(getApplication(), Constants.CURRENT_PET_ID_KEY, 0)
        val currentId = currentIdFlow.first()
        val newId = (currentId ?: 0) + 1

        dataStoreManager.putUserPreference(getApplication(), Constants.CURRENT_PET_ID_KEY, newId)

        return newId
    }

    private fun getCurrentDateParts(): List<String> {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(Date()).split("-")
    }
}