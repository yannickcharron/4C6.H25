package ca.qc.cstj.remotedatasource.data.datasources

import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.model.Planet
import ca.qc.cstj.remotedatasource.model.PlanetJSON
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json


class PlanetDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveAll() : List<Planet> {
        val (req, res, result) = Constants.BaseURL.PLANETS.httpGet().responseJson()

        return when(result) {
            is Result.Success -> {
                json.decodeFromString(result.value.content)
            }
            is Result.Failure -> {
                throw result.error.exception
            }
        }
    }

    fun retrieveOne() {

    }

    //Méthode non utilisée Serialisation manuelle pour exemple
    fun retrieveAllManual() : List<PlanetJSON> {

        val (req, res, result) = Constants.BaseURL.PLANETS.httpGet().responseJson()

        when(result) {
            is Result.Success -> {
                val planetsJSON = result.value.array()
                val planets = mutableListOf<PlanetJSON>()

                for(i in 0 until planetsJSON.length()) {
                    val planetJSON = planetsJSON.getJSONObject(i)
                    val planet = PlanetJSON(planetJSON)
                    planets.add(planet)

                }

                return planets
            }
            is Result.Failure -> {
                throw result.error.exception
            }
        }

    }

}