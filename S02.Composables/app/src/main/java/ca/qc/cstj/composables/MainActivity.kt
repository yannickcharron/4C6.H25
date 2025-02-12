package ca.qc.cstj.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ca.qc.cstj.composables.ui.components.BottomBar
import ca.qc.cstj.composables.ui.components.TopBar
import ca.qc.cstj.composables.ui.navigation.Destination
import ca.qc.cstj.composables.ui.screens.main.MainScreen
import ca.qc.cstj.composables.ui.screens.meditation.MeditationScreen
import ca.qc.cstj.composables.ui.screens.title.TitleScreen
import ca.qc.cstj.composables.ui.theme.ComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposablesTheme(dynamicColor = false) {
                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = Destination.Title
                ) {
                    composable<Destination.Title> {
                        TitleScreen(navigateToMain = {
                            navController.navigate(Destination.Main(it))
                        })
                    }
                    composable<Destination.Main> {
                        val args = it.toRoute<Destination.Main>()
                        MainScreen(name = args.name)
                    }
                }
            }
        }
    }


}

