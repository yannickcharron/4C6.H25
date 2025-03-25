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
import ca.qc.cstj.remotedatasource.ui.navigation.Destination
import ca.qc.cstj.remotedatasource.ui.screens.planets.details.PlanetDetailsScreen
import ca.qc.cstj.remotedatasource.ui.screens.planets.list.PlanetListScreen
import ca.qc.cstj.remotedatasource.ui.theme.RemoteDataSourceTheme

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
                            PlanetListScreen()
                        }
                        composable<Destination.PlanetDetails> {
                            PlanetDetailsScreen()
                        }
                    }

                }
            }
        }
    }
}
