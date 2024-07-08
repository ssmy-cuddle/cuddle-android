package com.ssmy.cuddle.di

import com.ssmy.cuddle.ui.intro.IntroViewModel
import com.ssmy.cuddle.ui.login.LoginViewModel
import com.ssmy.cuddle.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * doc 주석
 * @author wookjin
 * @since 7/8/24
 **/
val appModule = module {
    // ViewModel 주입
    viewModel { IntroViewModel() }
    viewModel { LoginViewModel() }
    viewModel { MainViewModel() }

    // Repository 주입 (필요시 추가)
    // single { AuthRepository(get()) }
}