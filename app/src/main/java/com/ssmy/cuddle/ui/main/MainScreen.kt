package com.ssmy.cuddle.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ssmy.cuddle.ui.main.screens.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val navController = rememberNavController()

//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        }
//    ) {
//        Box(modifier = Modifier.padding(it)) {
//            MainNavHost(navController = navController)
//        }
//    }
}
//
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val items = listOf(
//        BottomNavItem.Home,
//        BottomNavItem.Journey,
//        BottomNavItem.Community,
//        BottomNavItem.Donation,
//        BottomNavItem.Profile
//    )
//    BottomNavigation {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        items.forEach { item ->
//            BottomNavigationItem(
//                icon = { Icon(item.icon, contentDescription = null) },
//                label = { Text(item.title) },
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        popUpTo(navController.graph.startDestinationId) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun MainNavHost(navController: NavController) {
//    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
//        composable(BottomNavItem.Home.route) { HomeScreen() }
////        composable(BottomNavItem.Journey.route) { JourneyScreen() }
////        composable(BottomNavItem.Community.route) { CommunityScreen() }
////        composable(BottomNavItem.Donation.route) { DonationScreen() }
////        composable(BottomNavItem.Profile.route) { ProfileScreen() }
//    }
//}
//
//sealed class BottomNavItem(var title: String, var icon: androidx.compose.ui.graphics.vector.ImageVector, var route: String) {
//    object Home : BottomNavItem("홈", Icons.Default.Home, "home")
//    object Journey : BottomNavItem("여정", Icons.Default.Map, "journey")
//    object Community : BottomNavItem("커뮤니티", Icons.Default.Group, "community")
//    object Donation : BottomNavItem("기부", Icons.Default.Favorite, "donation")
//    object Profile : BottomNavItem("프로필", Icons.Default.Person, "profile")
//}
