package ca.qc.cstj.bottomnavigation.data.datasources.weather

import ca.qc.cstj.bottomnavigation.core.Constants
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class CurrentWeatherDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    fun retrieveWithCityName(cityName: String): CurrentWeatherDTO {

        val url = String.format(Constants.NetworkEndPoint.CURRENT_WEATHER_URL, cityName)

        val (_, _, result) = url.httpGet().responseJson()

        return when(result) {
            is Result.Success -> json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error
        }

    }
}