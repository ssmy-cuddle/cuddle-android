package com.ssmy.cuddle.ui.main.contents.profile.view.activitys

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.ssmy.cuddle.R
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.databinding.ActivityUserProfileBinding
import com.ssmy.cuddle.ui.base.BaseActivity
import com.ssmy.cuddle.ui.main.contents.profile.model.UserProfileViewModelFactory
import com.ssmy.cuddle.ui.main.contents.profile.viewmodels.UserProfileViewModel

class UserProfileActivity : BaseActivity<UserProfileViewModel>() {

    override val viewModel: UserProfileViewModel by viewModels {
        UserProfileViewModelFactory(application, DataStoreManager)
    }
    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        viewModel.isLoginEnabled.observe(this) { isEnabled ->
            binding.btnSaveProfile.isEnabled = isEnabled
        }

        binding.editNickname.addTextChangedListener {
            viewModel.updateLoginState(
                binding.editNickname.text.toString(),
                binding.editBio.text.toString()
            )
        }
        binding.editBio.addTextChangedListener {
            viewModel.updateLoginState(
                binding.editNickname.text.toString(),
                binding.editBio.text.toString()
            )
        }

        binding.btnSaveProfile.setOnClickListener {
            val nickname = binding.editNickname.text.toString()
            val bio = binding.editBio.text.toString()

            viewModel.saveUserData(nickname, bio)
            finish()
        }

        viewModel.loadUserData().observe(this) { userData ->
            binding.editNickname.setText(userData.nickname)
            binding.editBio.setText(userData.bio)
        }
    }
}