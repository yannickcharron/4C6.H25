package ca.qc.cstj.inkify.models

import kotlinx.serialization.Serializable

@Serializable
data class InkifySettings(
    val name: String = "",
    val noteDefaultColor: String = ""
)