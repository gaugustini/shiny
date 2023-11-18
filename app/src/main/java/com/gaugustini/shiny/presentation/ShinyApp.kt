package com.gaugustini.shiny.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gaugustini.shiny.presentation.search.SearchScreen

@Composable
fun ShinyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(
                onSearchClick = {
                    navController.navigate("result")
                }
            )
        }
        composable("result") {

        }
    }
}
