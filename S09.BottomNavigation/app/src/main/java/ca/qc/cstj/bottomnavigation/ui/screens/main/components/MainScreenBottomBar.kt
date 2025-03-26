package ca.qc.cstj.bottomnavigation.ui.screens.main.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ca.qc.cstj.bottomnavigation.core.navigation.DestinationNavigationItem
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination

@Composable
fun MainScreenBottomBar(
    navController: NavController,
    items : List<DestinationNavigationItem<out Destination>>
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(text = stringResource(item.labelId)) },
                icon = { NavigationBarItemIcon(item) },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(item.destination::class) } == true,
                onClick = {
                    navController.navigate(item.destination) {
                        //Éliminer les autres destinations et revenir à la destination
                        //de départ avant de naviguer vers une autre destination
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        //Évite d'avoir plusieurs copies de la destination dans le backstack
                        launchSingleTop = true
                        // Si nous avons déjà navigué vers cet écran conserver l'état à notre retour
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun NavigationBarItemIcon(item: DestinationNavigationItem<out Destination>) {
    Icon(
        modifier = Modifier.size(24.dp),
        imageVector = item.icon,
        contentDescription = stringResource(item.labelId)
    )
}