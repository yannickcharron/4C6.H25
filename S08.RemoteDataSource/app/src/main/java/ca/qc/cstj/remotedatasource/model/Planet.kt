package ca.qc.cstj.remotedatasource.model

import org.json.JSONObject


data class Planet(
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
