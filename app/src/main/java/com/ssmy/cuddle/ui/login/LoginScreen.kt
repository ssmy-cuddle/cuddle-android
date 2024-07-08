package com.ssmy.cuddle.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
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

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Cuddle", style = MaterialTheme.typography.h4)
//            Text("발에서 마음까지", style = MaterialTheme.typography.subtitle1)
//            Spacer(modifier = Modifier.height(32.dp))
//            Button(onClick = { viewModel.handleIntent(LoginContract.Intent.Login) }) {
//                Text("카카오톡으로 로그인")
//            }
//        }
//    }

    if (state.isLoading) {
        // 로딩 중 로직 (예: ProgressBar)
    }

    state.error?.let { error ->
        // 에러 메시지 표시 로직 (예: Snackbar)
    }
}