package com.ssmy.cuddle.ui.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

fun IntroScreen(navController: NavController, viewModel: IntroViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState().value
    val effect = remember { viewModel.effect }

    LaunchedEffect(effect) {
        effect.collect { effectValue ->
            when (effectValue) {
                is IntroContract.Effect.NavigateToLogin -> {
                    navController.navigate("login")
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.cuddle_logo), contentDescription = null)
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                "Cuddle",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NPSFontFamily
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    val navController = rememberNavController()
    val viewModel = IntroViewModel()

    IntroScreen(navController, viewModel)
}