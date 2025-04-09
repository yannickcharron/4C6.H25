package ca.qc.cstj.bottomnavigation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckIn(
    val scanId:String,
    val door: Int,
    val scannedDate: String = ""
)