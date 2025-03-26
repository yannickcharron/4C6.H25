package ca.qc.cstj.bottomnavigation.data.datasources

import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.model.Planet

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class PlanetDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveAll() : List<Planet> {
        val (_, _, result) = Constants.BaseURL.PLANETS.httpGet().responseJson()

        return when (result) {
            is Result.Success -> {
                json.decodeFromString(result.value.content)
            }
            is Result.Failure -> {
                throw result.error.exception
            }
        }

    }

    fun retrieveOne(href: String): Planet {
        val (_, _, result) = href.httpGet().responseJson()

        return when (result) {
            is Result.Success -> { json.decodeFromString(result.value.content) }
            is Result.Failure -> { throw result.error.exception }
        }
    }

}