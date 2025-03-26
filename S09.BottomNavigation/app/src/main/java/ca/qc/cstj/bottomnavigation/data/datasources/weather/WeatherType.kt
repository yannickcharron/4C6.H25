package ca.qc.cstj.bottomnavigation.data.datasources.weather

import androidx.annotation.DrawableRes
import ca.qc.cstj.bottomnavigation.R
import ca.qc.cstj.bottomnavigation.core.Constants
import kotlinx.datetime.LocalDateTime

sealed class WeatherType(
    val weatherDescription: String, @DrawableRes val id: Int
) {
    data object ClearSkyDay : WeatherType(
        weatherDescription = Constants.WeatherConditions.CLEAR_SKY, id = R.drawable.clear_sky_day
    )

    data object ClearSkyNight : WeatherType(
        weatherDescription = Constants.WeatherConditions.CLEAR_SKY, id = R.drawable.clear_sky_night
    )

    data object FewCloudsDay : WeatherType(
        weatherDescription = Constants.WeatherConditions.FEW_CLOUDS, id = R.drawable.few_clouds_day
    )

    data object FewCloudsNight : WeatherType(
        weatherDescription = Constants.WeatherConditions.FEW_CLOUDS, id = R.drawable.clear_sky_night
    )

    data object ScatteredClouds : WeatherType(
        weatherDescription = Constants.WeatherConditions.SCATTERED_CLOUDS, id = R.drawable.scattered_clouds
    )

    data object BrokenCloudsDay : WeatherType(
        weatherDescription = Constants.WeatherConditions.BROKEN_CLOUDS, id = R.drawable.broken_clouds_day
    )

    data object BrokenCloudsNight : WeatherType(
        weatherDescription = Constants.WeatherConditions.BROKEN_CLOUDS, id = R.drawable.broken_clouds_night
    )

    data object ShowerRainDay : WeatherType(
        weatherDescription = Constants.WeatherConditions.SHOWER_RAIN, id = R.drawable.rain_day
    )

    data object ShowerRainNight : WeatherType(
        weatherDescription = Constants.WeatherConditions.SHOWER_RAIN, id = R.drawable.rain_night
    )

    data object RainDay : WeatherType(
        weatherDescription = Constants.WeatherConditions.RAIN, id = R.drawable.rain_day
    )

    data object RainNight : WeatherType(
        weatherDescription = Constants.WeatherConditions.RAIN, id = R.drawable.rain_day
    )

    data object Thunderstorm : WeatherType(
        weatherDescription = Constants.WeatherConditions.THUNDERSTORM, id = R.drawable.thunderstorrm
    )

    data object Snow : WeatherType(
        weatherDescription = Constants.WeatherConditions.SNOW, id = R.drawable.snow
    )

    data object MistDay : WeatherType(
        weatherDescription = Constants.WeatherConditions.MIST, id = R.drawable.mist_day
    )

    data object MistNight : WeatherType(
        weatherDescription = Constants.WeatherConditions.MIST, id = R.drawable.mist_night
    )

    companion object {
        fun factory(
            mainDescription: String,
            weatherDescription: String,
            datetime : LocalDateTime
        ): WeatherType {
            when (mainDescription) {
                Constants.MainWeatherConditions.CLOUDS -> {
                    return if (weatherDescription == ScatteredClouds.weatherDescription) {
                        ScatteredClouds
                    } else if (weatherDescription == FewCloudsDay.weatherDescription) {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            FewCloudsNight
                        } else {
                            FewCloudsDay
                        }
                    } else {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            BrokenCloudsNight
                        } else {
                            BrokenCloudsDay
                        }
                    }
                }

                Constants.MainWeatherConditions.RAIN -> {
                    return if (weatherDescription == ShowerRainDay.weatherDescription) {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            ShowerRainNight
                        } else {
                            ShowerRainDay
                        }
                    } else {
                        if (datetime.hour > Constants.NIGHT_HOUR) {
                            RainNight
                        } else {
                            RainDay
                        }
                    }
                }

                Constants.MainWeatherConditions.THUNDERSTORM -> {
                    return Thunderstorm
                }

                Constants.MainWeatherConditions.SNOW -> {
                    return Snow
                }

                Constants.MainWeatherConditions.CLEAR -> {
                    return if (datetime.hour > Constants.NIGHT_HOUR) {
                        ClearSkyNight
                    } else {
                        ClearSkyDay
                    }
                }

                else -> {
                    return if (datetime.hour > Constants.NIGHT_HOUR) {
                        MistNight
                    } else {
                        MistDay
                    }
                }
            }
        }

        // false -> day
        // true -> night
        private fun checkTime(hour: String): Boolean {
            return if ((hour.substring(0, 2) == "00" || hour.substring(
                    0,
                    2
                ) == "03" || hour.substring(0, 2) == "06") && hour.substring(3, 5) == "AM"
            ) {
                true
            } else if (hour.substring(0, 2) == "12" && hour.substring(3, 5) == "AM") {
                true
            } else (hour.substring(0, 2) == "09") && hour.substring(3, 5) == "PM"
        }
    }
}