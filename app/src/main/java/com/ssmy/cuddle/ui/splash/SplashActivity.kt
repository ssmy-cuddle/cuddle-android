package com.ssmy.cuddle.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.ssmy.cuddle.R
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.ui.login.UserLoginActivity
import com.ssmy.cuddle.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels {
        SplashViewModelFactory(application, DataStoreManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.navigateToMain.observe(this) { navigateToMain ->
            val intent = if (navigateToMain) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, UserLoginActivity::class.java)
            }
            val options = ActivityOptionsCompat.makeCustomAnimation(
                this, R.anim.fade_in, R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
            finish()
        }

        lifecycleScope.launch {
            delay(2000)
            viewModel.checkLoginStatus()
        }

    }
}