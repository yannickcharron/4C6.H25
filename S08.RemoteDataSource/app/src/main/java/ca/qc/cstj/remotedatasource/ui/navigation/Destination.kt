package ca.qc.cstj.remotedatasource.ui.navigation

import kotlinx.serialization.Serializable


sealed interface Destination {

    @Serializable
    data object PlanetList : Destination

    @Serializable
    data object PlanetDetails: Destination

}