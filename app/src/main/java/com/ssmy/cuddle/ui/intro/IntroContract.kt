package com.ssmy.cuddle.ui.intro

/**
 * doc 주석
 * @author wookjin
 * @since 7/8/24
 **/
interface IntroContract {
    sealed class Intent {
        object NavigateToLogin : Intent()
    }

    data class State(
        val isLoading: Boolean = true
    )

    sealed class Effect {
        object NavigateToLogin : Effect()
    }
}
