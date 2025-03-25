package ca.qc.cstj.remotedatasource.ui.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import ca.qc.cstj.remotedatasource.model.Planet
import kotlinx.serialization.json.Json

object CustomNavType {

    val PlanetType = object : NavType<Planet>(isNullableAllowed = false) {
        override fun put(bundle: Bundle, key: String, value: Planet) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: Bundle, key: String): Planet? {
            val planetJson = bundle.getString(key) ?: "" //le json de la planet
            return Json.decodeFromString(planetJson)
        }

        override fun parseValue(value: String): Planet {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Planet): String {
            return Uri.encode(Json.encodeToString(value))
        }

    }

}