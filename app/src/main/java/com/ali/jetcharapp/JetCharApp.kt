package com.ali.jetcharapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ali.jetcharapp.ui.components.BottomBar
import com.ali.jetcharapp.ui.navigation.Screen
import com.ali.jetcharapp.ui.screen.detail.DetailScreen
import com.ali.jetcharapp.ui.screen.home.HomeScreen
import com.ali.jetcharapp.ui.screen.about.AboutPageScreen

@Composable
fun JetCharApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailCharacter.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {  innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { characterId ->
                        navController.navigate(Screen.DetailCharacter.createRoute(characterId))
                    }
                )
            }
            composable(Screen.About.route){
                AboutPageScreen()
            }
            composable(
                route = Screen.DetailCharacter.route,
                arguments = listOf(navArgument("characterId") {type = NavType.LongType}),
            ) {
                val id = it.arguments?.getLong("characterId") ?: -1L
                DetailScreen(
                    characterId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JetCharacterAppPreview() {
    JetCharApp()
}