package com.ssmy.cuddle.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ssmy.cuddle.R
import com.ssmy.cuddle.ui.theme.NPSFontFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState().value
    val effect = remember { viewModel.effect }

    LaunchedEffect(effect) {
        effect.collect { effectValue ->
            when (effectValue) {
                is LoginContract.Effect.NavigateToMain -> {
                    navController.navigate("main")
                }

                is LoginContract.Effect.ShowError -> {
                    // 에러 메시지 표시 로직 (예: Toast)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Image(painter = painterResource(id = R.drawable.cuddle_logo), contentDescription = null)
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    "Cuddle",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = NPSFontFamily
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "발에서 마음까지",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = NPSFontFamily
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Button(
            onClick = { viewModel.handleIntent(LoginContract.Intent.Login) },
            colors = ButtonDefaults.buttonColors(Color(0xFFFEE500)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 22.dp, end = 22.dp, bottom = 60.dp)
                .fillMaxWidth()
                .height(44.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kakao_symbol),
                        contentDescription = "카카오톡 심볼",
                        colorFilter = ColorFilter.tint(Color.Black),
                    )
                }
                Text(
                    "카카오톡 로그인",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xDD000000),
                    fontFamily = NPSFontFamily,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }


    if (state.isLoading) {
        // 로딩 중 로직 (예: ProgressBar)
    }

    state.error?.let { error ->
        // 에러 메시지 표시 로직 (예: Snackbar)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val viewModel = LoginViewModel()

    LoginScreen(navController, viewModel)
}