package com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.MovieViewModel
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.ROUTE_SCREEN
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.GetLayoutScreenOne
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.GetLayoutScreenThree
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.GetLayoutScreenTwo
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.LoginScreen
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.MovieFormScreen
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.MovieScreen

@Composable
fun GetLayoutScreenNavigation() {

    val navController = rememberNavController()
    val mainViewModel: MovieViewModel = viewModel()
    val moviesState = mainViewModel.movies.observeAsState(initial = emptyList())
    NavHost(navController = navController, startDestination = ROUTE_SCREEN.login.name) {
        composable(ROUTE_SCREEN.login.name) { LoginScreen(navController) }
        composable(ROUTE_SCREEN.add.name) { MovieFormScreen(navController,
            mainViewModel, null) }
        composable(
            "${ROUTE_SCREEN.edit.name}/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.StringType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("filmId")?.let { filmId ->
                MovieFormScreen(navController, mainViewModel, filmId)
            }
        }
        composable(ROUTE_SCREEN.movie.name) {
//            Movie(
//                moviesState.value,
//                navController
//            )
            MovieScreen(navController,mainViewModel)
        }
        composable(ROUTE_SCREEN.screen1.name) { GetLayoutScreenOne(navController) }
        composable(ROUTE_SCREEN.screen2.name) { GetLayoutScreenTwo(navController) }
        composable(ROUTE_SCREEN.screen3.name) { GetLayoutScreenThree(navController) }
    }


}