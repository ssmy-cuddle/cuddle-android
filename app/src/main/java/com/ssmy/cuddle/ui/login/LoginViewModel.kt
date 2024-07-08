package com.ssmy.cuddle.ui.login

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
class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginContract.State())
    val state: StateFlow<LoginContract.State> get() = _state

    private val _effect = MutableSharedFlow<LoginContract.Effect>()
    val effect: SharedFlow<LoginContract.Effect> get() = _effect

    fun handleIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.Login -> login()
        }
    }

    private fun login() {
//        viewModelScope.launch {
//            _state.value = LoginContract.State(isLoading = true)
//            UserApiClient.instance.loginWithKakaoTalk { token, error ->
//                if (error != null) {
//                    setEffect { LoginContract.Effect.ShowError(error.message ?: "Login failed") }
//                } else if (token != null) {
//                    // 토큰 저장 로직 (예: SharedPreferences)
//                    setEffect { LoginContract.Effect.NavigateToMain }
//                }
//            }
//        }
    }

    private fun setEffect(builder: () -> LoginContract.Effect) {
        val effectValue = builder()
        viewModelScope.launch {
            _effect.emit(effectValue)
        }
    }
}