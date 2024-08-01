package com.ssmy.cuddle.ui.base

import androidx.appcompat.app.AppCompatActivity

/**
 * BaseActivity는 공통 기능을 제공하는 베이스 액티비티입니다.
 *
 * @author wookjin
 * @since 7/31/24
 **/
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    abstract val viewModel: VM
}