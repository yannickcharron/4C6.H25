package ca.qc.cstj.inkify.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object AddNote : Destination()

    @Serializable
    data object Settings : Destination()

    @Serializable
    data object NotesList: Destination()
}