package com.ssmy.cuddle.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.ssmy.cuddle.R
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.databinding.ActivityUserLoginBinding
import com.ssmy.cuddle.ui.base.BaseActivity
import com.ssmy.cuddle.ui.main.MainActivity

class UserLoginActivity : BaseActivity<UserLoginViewModel>() {
    override val viewModel: UserLoginViewModel by viewModels {
        UserLoginViewModelFactory(application, DataStoreManager)
    }
    private lateinit var binding: ActivityUserLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_login)

        viewModel.isLoginEnabled.observe(this) { isEnabled ->
            binding.btnLogin.isEnabled = isEnabled
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

        binding.btnLogin.setOnClickListener {
            val nickname = binding.editNickname.text.toString()
            val bio = binding.editBio.text.toString()

            viewModel.saveUserData(nickname, bio)
            navigateToMainActivity()
        }

        viewModel.loadUserData().observe(this) { userData ->
            binding.editNickname.setText(userData.nickname)
            binding.editBio.setText(userData.bio)
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}