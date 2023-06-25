package com.dicoding.composesubmission

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.dicoding.composesubmission.api.ApiConfig
import com.dicoding.composesubmission.screen.Profile
import com.dicoding.composesubmission.screen.detail.CharacterDetail
import com.dicoding.composesubmission.screen.home.HomePage
import com.dicoding.composesubmission.screen.home.HomeViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomePage(navController = navController, viewModel = HomeViewModel())
        }
        composable("detail_screen/{uuid}") { backStackEntry ->
            val uuid = backStackEntry.arguments?.getString("uuid")
            CharacterDetail(
                navController = navController,
                viewModel = HomeViewModel(),
                uuid = uuid
            )
        }
        composable("profile") {
            Profile(navController = navController)
        }
    }
    BackHandler(onBack = {
        if (navController.currentDestination?.route == "detail_screen/{uuid}") {
            navController.popBackStack("home_screen", inclusive = true)
        }
    })
}

