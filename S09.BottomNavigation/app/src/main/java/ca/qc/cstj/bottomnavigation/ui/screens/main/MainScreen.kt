package ca.qc.cstj.bottomnavigation.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination
import ca.qc.cstj.bottomnavigation.ui.navigation.fromRoute
import ca.qc.cstj.bottomnavigation.ui.screens.planets.details.PlanetDetailsScreen
import ca.qc.cstj.bottomnavigation.ui.screens.planets.list.PlanetListScreen

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {

    //TODO: val navigationItems =

    val navController = rememberNavController()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry.value.fromRoute()

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffectFlow) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffectFlow.collect { sideEffect ->
                //TODO:
            }
        }
    }

    Scaffold(
        topBar = {
            //TODO:
        },
        bottomBar = {
            //TODO:
        },
        snackbarHost = {
            //TODO:
    }) { innerPaddings ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            navController = navController,
            startDestination = Destination.PlanetList
        ) {
            composable<Destination.PlanetList> {
                PlanetListScreen(toPlanetDetailScreen = { href ->
                    navController.navigate(Destination.PlanetDetail(href))
                })
            }
            composable<Destination.PlanetDetail> {
                PlanetDetailsScreen()
            }
        }

    }

}