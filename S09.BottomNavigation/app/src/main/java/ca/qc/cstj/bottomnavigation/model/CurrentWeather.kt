package ca.qc.cstj.bottomnavigation.model

import ca.qc.cstj.bottomnavigation.core.DateHelper
import ca.qc.cstj.bottomnavigation.data.datasources.weather.CurrentWeatherDTO

data class CurrentWeather(val currentWeatherDTO: CurrentWeatherDTO) {
    val city = currentWeatherDTO.name
    val country = currentWeatherDTO.sys?.country
    val temperature = currentWeatherDTO.main?.temp
    val feelsLike = currentWeatherDTO.main?.feelsLike
    val weather = currentWeatherDTO.weather?.get(0)?.main
    val description = currentWeatherDTO.weather?.get(0)?.description
    val icon = currentWeatherDTO.weather?.get(0)?.icon
    val latitude = currentWeatherDTO.coord?.lat
    val longitude = currentWeatherDTO.coord?.lon
    val systemDate = DateHelper.toSystemDefaultDateTime(currentWeatherDTO.dt?.toLong() ?:0 , currentWeatherDTO.timezone ?: 0)
    val locationDateTime = DateHelper.toCurrentWeatherLocationDateTime(currentWeatherDTO.dt?.toLong() ?:0 , currentWeatherDTO.timezone ?: 0)



}
