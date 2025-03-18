package ca.qc.cstj.remotedatasource.data.datasources

import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.model.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result


class PlanetDataSource {

    fun retrieveAll() : List<Planet> {

        val (req, res, result) = Constants.BaseURL.PLANETS.httpGet().responseJson()

        when(result) {
            is Result.Success -> {
                val planetsJSON = result.value.array()
                val planets = mutableListOf<Planet>()

                for(i in 0 until planetsJSON.length()) {
                    val planetJSON = planetsJSON.getJSONObject(i)
                    val planet = Planet(planetJSON)
                    planets.add(planet)

                }

                return planets
            }
            is Result.Failure -> {
                throw result.error.exception
            }
        }

    }

    fun retrieveOne() {

    }

}