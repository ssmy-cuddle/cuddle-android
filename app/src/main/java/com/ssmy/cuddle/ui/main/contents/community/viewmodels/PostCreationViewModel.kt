package com.ssmy.cuddle.ui.main.contents.community.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssmy.cuddle.ui.base.BaseViewModel

/**
 * doc 주석
 * @author wookjin
 * @since 9/4/24
 **/
class PostCreationViewModel(
    application: Application,
) : BaseViewModel(application) {

    private val _selectedPhotos = MutableLiveData<List<Uri>>()
    val selectedPhotos: LiveData<List<Uri>> = _selectedPhotos

    private val _isAddPhotoButtonEnabled = MutableLiveData(true)
    val isAddPhotoButtonEnabled: LiveData<Boolean> = _isAddPhotoButtonEnabled

    private val _photoCountText = MutableLiveData("0")
    val photoCountText: LiveData<String> = _photoCountText

    private val maxPhotos = 5

    init {
        _selectedPhotos.value = emptyList()
        updatePhotoCount()
    }

    fun addPhotos(photos: List<Uri>) {
        _selectedPhotos.value = _selectedPhotos.value?.toMutableList()?.apply {
            addAll(photos)
        }
        updateAddPhotoButtonEnabled()
        updatePhotoCount()
    }

    fun removePhoto(uri: Uri) {
        _selectedPhotos.value = _selectedPhotos.value?.filter { it != uri }
        updateAddPhotoButtonEnabled()
        updatePhotoCount()
    }

    private fun updateAddPhotoButtonEnabled() {
        _isAddPhotoButtonEnabled.value = (_selectedPhotos.value?.size ?: 0) < maxPhotos
    }

    private fun updatePhotoCount() {
        _photoCountText.value = "${_selectedPhotos.value?.size ?: 0}"
    }
}