package ca.qc.cstj.bottomnavigation.model

import ca.qc.cstj.bottomnavigation.core.DateHelper
import ca.qc.cstj.bottomnavigation.data.datasources.weather.CurrentWeatherDTO

data class CurrentWeather(val currentWeatherDTO: CurrentWeatherDTO) {
    val city = currentWeatherDTO.name ?: ""
    val country = currentWeatherDTO.sys?.country ?: ""
    val temperature = currentWeatherDTO.main?.temp ?: 0.0
    val feelsLike = currentWeatherDTO.main?.feelsLike ?: 0.0
    val weather = currentWeatherDTO.weather?.get(0)?.main ?: ""
    val description = currentWeatherDTO.weather?.get(0)?.description ?: ""
    val icon = currentWeatherDTO.weather?.get(0)?.icon ?: ""
    val latitude = currentWeatherDTO.coord?.lat ?: 0.0
    val longitude = currentWeatherDTO.coord?.lon ?: 0.0
    val systemDate = DateHelper.toSystemDefaultDateTime(currentWeatherDTO.dt?.toLong() ?:0 , currentWeatherDTO.timezone ?: 0)
    val locationDateTime = DateHelper.toCurrentWeatherLocationDateTime(currentWeatherDTO.dt?.toLong() ?:0 , currentWeatherDTO.timezone ?: 0)



}
