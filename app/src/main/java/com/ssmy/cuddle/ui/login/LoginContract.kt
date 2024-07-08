package com.ssmy.cuddle.ui.login

/**
 * doc 주석
 * @author wookjin
 * @since 7/8/24
 **/
interface LoginContract {
    sealed class Intent {
        object Login : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null
    )

    sealed class Effect {
        object NavigateToMain : Effect()
        data class ShowError(val message: String) : Effect()
    }
}