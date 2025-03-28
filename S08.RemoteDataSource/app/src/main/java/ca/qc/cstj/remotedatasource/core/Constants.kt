package ca.qc.cstj.remotedatasource.core

object Constants {

    const val IMAGE_URL = "https://assets.andromia.science/planets/%s.png"

    object BaseURL {
        private const val BASE_API = "https://api.andromia.science"
        const val PLANETS = "${BASE_API}/planets"
    }

    enum class TemperatureUnit {
        Kelvin,
        Celsius,
        Fahrenheit
    }

    object RefreshDelays {
        const val PLANETS_REFRESH_TIMER = 60000L
    }
}