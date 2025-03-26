package ca.qc.cstj.bottomnavigation.data.repositories

import ca.qc.cstj.bottomnavigation.data.datasources.weather.CurrentWeatherDataSource

class CurrentWeatherRepository {

    private val currentWeatherDataSource: CurrentWeatherDataSource = CurrentWeatherDataSource()

    fun retrieveWithCityName(cityName: String)  {
        //TODO:
    }
}