package com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.MainViewModel
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.ROUTE_SCREEN
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.GetLayoutScreenOne
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.GetLayoutScreenThree
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.GetLayoutScreenTwo
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.LoginScreen
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.Movie

@Composable
fun GetLayoutScreenNavigation() {

    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()
    val moviesState = mainViewModel.movies.observeAsState(initial = emptyList())
    NavHost(navController = navController, startDestination = ROUTE_SCREEN.login.name) {
        composable(ROUTE_SCREEN.login.name) { LoginScreen(navController) }
        composable(ROUTE_SCREEN.movie.name) {
            Movie(
                moviesState.value,
                navController
            )
        }
        composable(ROUTE_SCREEN.screen1.name) { GetLayoutScreenOne(navController) }
        composable(ROUTE_SCREEN.screen2.name) { GetLayoutScreenTwo(navController) }
        composable(ROUTE_SCREEN.screen3.name) { GetLayoutScreenThree(navController) }
    }


}