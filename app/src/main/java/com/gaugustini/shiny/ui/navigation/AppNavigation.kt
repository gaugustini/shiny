package com.gaugustini.shiny.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gaugustini.shiny.ui.screen.result.ResultScreen
import com.gaugustini.shiny.ui.screen.search.SearchScreen
import com.gaugustini.shiny.ui.theme.ShinyTheme

@Composable
fun AppNavigation() {
    ShinyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "search") {
                composable("search") {
                    SearchScreen(
                        toResultScreen = { navController.navigate("result") }
                    )
                }
                composable("result") {
                    ResultScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
