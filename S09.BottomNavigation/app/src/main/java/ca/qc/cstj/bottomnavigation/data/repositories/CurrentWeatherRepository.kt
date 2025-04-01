package ca.qc.cstj.bottomnavigation.data.repositories

import android.view.KeyEvent.DispatcherState
import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.core.data.ApiResult
import ca.qc.cstj.bottomnavigation.data.datasources.weather.CurrentWeatherDataSource
import ca.qc.cstj.bottomnavigation.model.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CurrentWeatherRepository {

    private val currentWeatherDataSource: CurrentWeatherDataSource = CurrentWeatherDataSource()

    fun retrieveWithCityName(cityName: String) : Flow<ApiResult<CurrentWeather>> {

        return flow {
            while(true) {
                try {
                    emit(ApiResult.Loading)
                    val currentWeatherDTO = currentWeatherDataSource.retrieveWithCityName(cityName)
                    //Transformation du DTO en object du model
                    //val currentWeather = CurrentWeather(currentWeatherDTO)
                    emit(ApiResult.Success(CurrentWeather(currentWeatherDTO)))
                } catch(ex:Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(Constants.RefreshDelay.METEO_REFRESH)
            }
        }.flowOn(Dispatchers.IO)

    }
}