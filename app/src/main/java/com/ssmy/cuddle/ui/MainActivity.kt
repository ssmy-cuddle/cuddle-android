package com.ssmy.cuddle.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ssmy.cuddle.ui.intro.IntroScreen
import com.ssmy.cuddle.ui.login.LoginScreen
import com.ssmy.cuddle.ui.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuddleApp()
        }
    }
}

@Composable
fun CuddleApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "intro") {
        composable("intro") { IntroScreen(navController = navController) }
        composable("login") { LoginScreen(navController = navController) }
        composable("main") { MainScreen(navController = navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CuddleApp()
}