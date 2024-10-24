package com.ssmy.cuddle.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ssmy.cuddle.R
import com.ssmy.cuddle.data.DataStoreManager
import com.ssmy.cuddle.databinding.ActivityLoginBinding
import com.ssmy.cuddle.ui.base.BaseActivity

class LoginActivity : BaseActivity<LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(application, DataStoreManager)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
//
//        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//                Log.e(TAG, "카카오계정으로 로그인 실패", error)
//            } else if (token != null) {
//                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
//            }
//        }
//
//        binding.loginButton.setOnClickListener {
//            // 카카오톡 설치 확인
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
//                // 카카오톡 로그인
//                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
//                    // 로그인 실패 부분
//                    if (error != null) {
//                        Log.e(TAG, "로그인 실패 $error")
//                        // 사용자가 취소
//                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
//                            return@loginWithKakaoTalk
//                        }
//                        // 다른 오류
//                        else {
//                            UserApiClient.instance.loginWithKakaoAccount(
//                                this,
//                                callback = callback
//                            ) // 카카오 이메일 로그인
//                        }
//                    }
//                    // 로그인 성공 부분
//                    else if (token != null) {
//                        Log.e(TAG, "로그인 성공 ${token.accessToken}")
//                    }
//                }
//            } else {
//                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback) // 카카오 이메일 로그인
//            }
//        }
    }

//    private fun apiTEST() {
//        // 회원가입 요청 데이터
//        val signUpRequest = SignUpRequest(
//            provider = Provider(provider = "kakao", access_token = "your_access_token"),
//            email = "user@example.com",
//            username = "username123",
//            full_name = "John Doe"
//        )
//
//        // 회원가입 API 호출
//        ApiManager.signUp(signUpRequest, onSuccess = { signUpResponse ->
//            Log.d("MainActivity", "SignUp Success: $signUpResponse")
//        }, onFailure = { error, validationError ->
//            Log.e("MainActivity", "Error: ${error.message}")
//            validationError?.let {
//                Log.e("MainActivity", "Validation Error: ${it.detail}")
//            }
//        })
//
//    }
//
//    private fun goMain() {
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
//    }
}