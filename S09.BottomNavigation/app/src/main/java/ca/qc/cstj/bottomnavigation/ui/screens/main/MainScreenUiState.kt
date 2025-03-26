package ca.qc.cstj.bottomnavigation.ui.screens.main

import ca.qc.cstj.bottomnavigation.ui.navigation.Destination

data class MainScreenUiState(
    val titleArgs: List<Any> = listOf(),
    val bottomBarBadges : Map<Destination, String> = mapOf()
)