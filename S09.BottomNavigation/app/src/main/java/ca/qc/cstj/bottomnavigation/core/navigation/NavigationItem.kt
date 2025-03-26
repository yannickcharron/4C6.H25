package ca.qc.cstj.bottomnavigation.core.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

open class NavigationItem(
    @StringRes val labelId: Int,
    val icon: ImageVector,
)

class ActionNavigationItem(
    @StringRes labelId: Int,
    icon: ImageVector,
    val action: () -> Unit
) : NavigationItem(labelId, icon)

class DestinationNavigationItem<T>(
    @StringRes labelId: Int,
    icon: ImageVector,
    val destination: T
) : NavigationItem(labelId, icon)


