package ca.qc.cstj.bottomnavigation.ui.navigation

import androidx.compose.ui.util.fastCbrt
import androidx.navigation.NavBackStackEntry
import ca.qc.cstj.bottomnavigation.R
import kotlinx.serialization.Serializable


sealed interface Destination {
    @Serializable
    data object PlanetList : Destination

    @Serializable
    data class PlanetDetail(val href: String) : Destination

    @Serializable
    data object CurrentWeather : Destination

    @Serializable
    data object Favorites : Destination

    @Serializable
    data object Profile: Destination

    @Serializable
    data object Barcode : Destination

    @Serializable
    data object Orientation: Destination

    @Serializable
    data class Map(val lat: Double, val lng: Double) : Destination

    @Serializable
    data object Login : Destination

    fun title() : Int {
        return when(this) {
            Barcode -> R.string.barcode
            CurrentWeather -> R.string.weather
            Favorites -> R.string.favorites
            Orientation -> R.string.orientation
            is PlanetDetail -> R.string.title_planet_detail
            PlanetList -> R.string.planets
            Profile -> R.string.profile
            is Map -> R.string.maps
            Login -> R.string.loading
        }
    }

    fun isNavigateUpVisible() : Boolean {
        return when(this) {
            is PlanetDetail -> true
            is Map -> true
            else -> false
        }
    }

    fun isBottomBarVisible(): Boolean {
        return when(this) {
            is Map -> false
            Login -> false
            else -> true
        }
    }

    fun isTopBarVisible() : Boolean {
        return when(this) {
            is Map -> false
            Login -> false
            else -> true
        }
    }
}

//https://proandroiddev.com/compose-navigation-2-8-0-f9ad34024624
fun NavBackStackEntry?.fromRoute() : Destination {
    this?.destination?.route?.
        substringBefore("?")?.
        substringBefore("/")?.
        substringAfterLast(".")?.
        let {
            return when(it) {
                Destination.PlanetList::class.simpleName ->  Destination.PlanetList
                Destination.PlanetDetail::class.simpleName -> Destination.PlanetDetail("")
                Destination.CurrentWeather::class.simpleName -> Destination.CurrentWeather
                Destination.Favorites::class.simpleName -> Destination.Favorites
                Destination.Profile::class.simpleName -> Destination.Profile
                Destination.Barcode::class.simpleName -> Destination.Barcode
                Destination.Orientation::class.simpleName -> Destination.Orientation
                Destination.Map::class.simpleName -> Destination.Map(0.0,0.0)
                Destination.Login::class.simpleName -> Destination.Login
                else -> Destination.CurrentWeather
            }
        }
    return Destination.CurrentWeather
}