package ca.qc.cstj.bottomnavigation.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.navigation.DestinationNavigationItem
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination
import ca.qc.cstj.bottomnavigation.ui.navigation.fromRoute
import ca.qc.cstj.bottomnavigation.ui.screens.barcode.BarcodeScreen
import ca.qc.cstj.bottomnavigation.ui.screens.main.components.MainScreenBottomBar
import ca.qc.cstj.bottomnavigation.ui.screens.main.components.MainScreenTopBar
import ca.qc.cstj.bottomnavigation.ui.screens.maps.MapScreen
import ca.qc.cstj.bottomnavigation.ui.screens.orientation.OrientationScreen
import ca.qc.cstj.bottomnavigation.ui.screens.planets.details.PlanetDetailsScreen
import ca.qc.cstj.bottomnavigation.ui.screens.planets.list.PlanetListScreen
import ca.qc.cstj.bottomnavigation.ui.screens.profile.ProfileScreen
import ca.qc.cstj.bottomnavigation.ui.screens.weather.CurrentWeatherSection
import ca.qc.cstj.bottomnavigation.ui.screens.weather.WeatherScreen

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {

    val navigationItems = listOf(
        DestinationNavigationItem(
            labelId = R.string.weather,
            icon = Icons.Default.WbSunny,
            destination = Destination.CurrentWeather
        ),
        DestinationNavigationItem(
            labelId = R.string.planets,
            icon = ImageVector.vectorResource(R.drawable.planet_24dp),
            destination = Destination.PlanetList
        ),
        DestinationNavigationItem(
            labelId = R.string.profile,
            icon = Icons.Default.Person4,
            destination = Destination.Profile
        ),
        DestinationNavigationItem(
            labelId = R.string.barcode,
            icon = Icons.Default.QrCodeScanner,
            destination = Destination.Barcode
        ),
        DestinationNavigationItem(
            labelId = R.string.orientation,
            icon = Icons.Default.Landscape,
            destination = Destination.Orientation
        )

    )

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
            if(currentDestination.isTopBarVisible()) {
                MainScreenTopBar(
                    currentDestination = currentDestination,
                    titleFormatArgs = uiState.titleArgs.toTypedArray(),
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        },
        bottomBar = {
            if(currentDestination.isBottomBarVisible()) {
                MainScreenBottomBar(
                    navController = navController,
                    items = navigationItems,
                    badges = uiState.bottomBarBadges
                )
            }
        },
        snackbarHost = {
            //TODO:
    }) { innerPaddings ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            navController = navController,
            startDestination = Destination.CurrentWeather
        ) {
            composable<Destination.PlanetList> {
                PlanetListScreen(toPlanetDetailScreen = { href ->
                    navController.navigate(Destination.PlanetDetail(href))
                })
            }
            composable<Destination.PlanetDetail> {
                PlanetDetailsScreen(
                    sendMainScreenSideEffect = { childrenSideEffect ->
                        viewModel.onSideEffect(childrenSideEffect)
                    }
                )
            }
            composable<Destination.CurrentWeather> {
                 WeatherScreen()
            }
            composable<Destination.Profile> {
                ProfileScreen(
                    sendMainScreenSideEffect = { childrenSideEffect ->
                        viewModel.onSideEffect(childrenSideEffect)
                    },
                    toMapScreen = {
                        navController.navigate(Destination.Map)
                    }
                )
            }
            composable<Destination.Barcode> {
                BarcodeScreen()
            }
            composable<Destination.Orientation> {
                OrientationScreen()
            }
            composable<Destination.Map> {
                MapScreen()
            }
        }

    }

}