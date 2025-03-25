package ca.qc.cstj.remotedatasource.ui.navigation

import ca.qc.cstj.remotedatasource.model.Planet
import kotlinx.serialization.Serializable


sealed interface Destination {

    @Serializable
    data object PlanetList : Destination

    @Serializable
    data class PlanetDetails(val planet: Planet): Destination

    @Serializable
    data class PlanetDetailsHref(val href: String) : Destination

}