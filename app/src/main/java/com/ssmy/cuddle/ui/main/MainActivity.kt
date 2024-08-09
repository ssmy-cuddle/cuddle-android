package com.ssmy.cuddle.ui.main

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ssmy.cuddle.R
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.databinding.ActivityMainBinding
import com.ssmy.cuddle.ui.base.BaseActivity
import com.ssmy.cuddle.ui.main.ViewModel.MainViewModel
import com.ssmy.cuddle.ui.main.ViewModel.MainViewModelFactory

class MainActivity : BaseActivity<MainViewModel>() {
    override val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(application, DataStoreManager)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}