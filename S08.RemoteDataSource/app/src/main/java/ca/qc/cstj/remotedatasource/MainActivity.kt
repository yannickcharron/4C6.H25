package ca.qc.cstj.remotedatasource

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ca.qc.cstj.remotedatasource.model.Planet
import ca.qc.cstj.remotedatasource.ui.navigation.CustomNavType
import ca.qc.cstj.remotedatasource.ui.navigation.Destination
import ca.qc.cstj.remotedatasource.ui.screens.planets.details.DetailsPlanetScreen
import ca.qc.cstj.remotedatasource.ui.screens.planets.details.DetailsPlanetScreenWithoutRefresh
import ca.qc.cstj.remotedatasource.ui.screens.planets.list.PlanetListScreen
import ca.qc.cstj.remotedatasource.ui.theme.RemoteDataSourceTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemoteDataSourceTheme {
                Scaffold { innerPaddings ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPaddings),
                        navController = navController,
                        startDestination = Destination.PlanetList
                    ) {
                        composable<Destination.PlanetList> {
                                //Version 1:
//                            PlanetListScreen(
//                                navigateToPlanetDetail = { planet -> navController.navigate(Destination.PlanetDetails(planet)) }
//                            )
                            PlanetListScreen(
                                navigateToPlanetDetail = { planet -> navController.navigate(Destination.PlanetDetailsHref(planet.href)) }
                            )
                        }
                        composable<Destination.PlanetDetails>(
                            typeMap = mapOf(
                                typeOf<Planet>() to CustomNavType.PlanetType
                            )
                        ) {
                            //Version 1: Parametre de type planet (avec une classe du modèle)
                            val planet = it.toRoute<Destination.PlanetDetails>().planet
                            DetailsPlanetScreenWithoutRefresh(planet)
                        }
                        composable<Destination.PlanetDetailsHref> {
                            DetailsPlanetScreen()
                        }
                    }

                }
            }
        }
    }
}
