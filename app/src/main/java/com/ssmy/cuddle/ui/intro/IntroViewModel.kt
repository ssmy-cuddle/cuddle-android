package com.ssmy.cuddle.ui.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
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
class IntroViewModel : ViewModel() {

    private val _state = MutableStateFlow(IntroContract.State())
    val state: StateFlow<IntroContract.State> get() = _state

    private val _effect = MutableSharedFlow<IntroContract.Effect>()
    val effect: SharedFlow<IntroContract.Effect> get() = _effect

    init {
        viewModelScope.launch {
            delay(2000)
            setEffect { IntroContract.Effect.NavigateToLogin }
        }
    }

    fun handleIntent(intent: IntroContract.Intent) {
        when (intent) {
            is IntroContract.Intent.NavigateToLogin -> {
                setEffect { IntroContract.Effect.NavigateToLogin }
            }
        }
    }

    private fun setEffect(builder: () -> IntroContract.Effect) {
        val effectValue = builder()
        viewModelScope.launch {
            _effect.emit(effectValue)
        }
    }
}