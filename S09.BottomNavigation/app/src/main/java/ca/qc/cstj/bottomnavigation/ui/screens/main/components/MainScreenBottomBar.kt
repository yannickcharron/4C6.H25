package ca.qc.cstj.bottomnavigation.ui.screens.main.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ca.qc.cstj.bottomnavigation.core.navigation.DestinationNavigationItem
import ca.qc.cstj.bottomnavigation.ui.navigation.Destination

@Composable
fun MainScreenBottomBar(
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {

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