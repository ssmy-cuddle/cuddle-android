package com.ssmy.cuddle.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * doc 주석
 * @author wookjin
 * @since 7/8/24
 **/
class MainViewModel : ViewModel() {

//    private val _state = MutableStateFlow(MainContract.State())
//    val state: StateFlow<MainContract.State> get() = _state

    private val _effect = MutableSharedFlow<MainContract.Effect>()
    val effect: SharedFlow<MainContract.Effect> get() = _effect

    fun handleIntent(intent: MainContract.Intent) {
        // Intent 처리 로직 구현
    }

    private fun setEffect(builder: () -> MainContract.Effect) {
        val effectValue = builder()
        viewModelScope.launch {
            _effect.emit(effectValue)
        }
    }
}
