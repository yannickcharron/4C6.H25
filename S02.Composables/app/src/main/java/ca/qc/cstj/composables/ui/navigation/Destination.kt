package ca.qc.cstj.composables.ui.navigation

import kotlinx.serialization.Serializable

//sealed implique abstract
//sealed tous les enfants sont à l'intérieur de la classe
sealed class Destination {
    @Serializable
    data object Title : Destination()
    @Serializable
    data class Main(val name: String) : Destination()
}