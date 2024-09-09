package com.ssmy.cuddle.ui.main.contents.profile.view.activitys

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.ActivityUserProfileBinding
import com.ssmy.cuddle.ui.base.BaseActivity
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.UserProfileViewModel

class UserProfileActivity : BaseActivity<UserProfileViewModel>() {

    override val viewModel: UserProfileViewModel by viewModels()
    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}