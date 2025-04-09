package ca.qc.cstj.bottomnavigation.core

object Constants {
    const val DOOR = 2
    const val DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val IMAGE_URL = "https://assets.andromia.science/planets/%s.png"
    const val NIGHT_HOUR = 19

    object BaseURL {
        private const val BASE_API = "https://api.andromia.science"
        const val PLANETS = "${BASE_API}/planets"
        const val CHECKIN_URL = "${BASE_API}/check-ins"
    }

    enum class TemperatureUnit {
        Kelvin,
        Celsius,
        Fahrenheit
    }

    object RefreshDelay {
        const val METEO_REFRESH= 1000L * 60
        const val NOTIFICATION_REFRESH = 1000L * 60
        const val PLANETS_REFRESH = 1000L * 60
    }

    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    //https://api.openweathermap.org/data/2.5/weather?q=Beirut&units=metric&appid=ae152a05c72c228055a11bfe56cd3ac2
    object NetworkEndPoint {
        private const val BASE_URL = "https://api.openweathermap.org"
        const val API_KEY = "ae152a05c72c228055a11bfe56cd3ac2"
        const val UNITS = "metric"
        const val FORECAST_END_POINT = "$BASE_URL/data/2.5/forecast"
        const val CURRENT_WEATHER_END_POINT = "$BASE_URL/data/2.5/weather"
        const val DEFAULTS_OPTIONS = "&units=${UNITS}&appid=${API_KEY}"
        const val WEATHER_ICON_URL = "https://openweathermap.org/img/w/%s.png"
        const val FLAGS_URL = "https://flagcdn.com/%s.svg"
        const val CURRENT_WEATHER_URL = "$CURRENT_WEATHER_END_POINT?q=%s$DEFAULTS_OPTIONS"
        const val FLAGS_API_URL = "https://flagsapi.com/%s/flat/64.png"
    }

    object WeatherConditions {
        const val CLEAR_SKY = "clear sky"
        const val FEW_CLOUDS = "few clouds"
        const val SCATTERED_CLOUDS = "scattered clouds"
        const val BROKEN_CLOUDS = "broken clouds"
        const val SHOWER_RAIN = "shower rain"
        const val RAIN = "rain"
        const val THUNDERSTORM = "thunderstorm"
        const val SNOW = "snow"
        const val MIST = "mist"
    }

    object MainWeatherConditions {
        const val CLOUDS = "Clouds"
        const val SNOW = "Snow"
        const val RAIN = "Rain"
        const val THUNDERSTORM = "Thunderstorm"
        const val CLEAR = "Clear"
    }
}