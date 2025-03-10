package com.example.my_application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.my_application.screens.HomeScreen
import com.example.my_application.screens.SearchScreen
import com.example.my_application.screens.PlaylistsScreen
import com.example.my_application.screens.SocialScreen
import com.example.my_application.screens.ProfileScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Playlists : Screen("playlists")
    object Social : Screen("social")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }
        composable(Screen.Playlists.route) {
            PlaylistsScreen()
        }
        composable(Screen.Social.route) {
            SocialScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
} 