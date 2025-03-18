package ca.qc.cstj.remotedatasource.model

import kotlinx.serialization.Serializable
import org.json.JSONObject

@Serializable
data class Planet(
    val href: String,
    val name: String,
    val icon: String,
    val temperature: Double,
    val satellites: List<String>,
    val portals : List<Portal>,
    val position: Position
)

@Serializable
data class Portal(val position: String, val affinity: String)

@Serializable
data class Position(val x: Double, val y: Double, val z: Double)

data class PlanetJSON(
    val name: String,
    val icon: String,
    val temperature: Double
) {
    constructor(json: JSONObject) : this(
        name = json.getString("name"),
        icon = json.getString("icon"),
        temperature = json.getDouble("temperature")
    )
}
