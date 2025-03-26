package ca.qc.cstj.bottomnavigation.data.repositories

import ca.qc.cstj.bottomnavigation.core.Constants
import ca.qc.cstj.bottomnavigation.core.data.ApiResult
import ca.qc.cstj.bottomnavigation.data.datasources.PlanetDataSource
import ca.qc.cstj.bottomnavigation.model.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlanetRepository {

    companion object {
        const val KELVIN_BASE = 273.15
        const val FAHRENHEIT_FACTOR = 1.8
        const val FAHRENHEIT_BASE = 32
    }

    private val planetDataSource = PlanetDataSource()

    fun retrieveOne(href: String) : Flow<ApiResult<Planet>> {
        return flow {
            while (true) {
                try {
                    emit(ApiResult.Loading)
                    emit(ApiResult.Success(planetDataSource.retrieveOne(href)))

                } catch(ex: Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(Constants.RefreshDelay.PLANETS_REFRESH)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun retrieveAllWithRefresh() : Flow<ApiResult<List<Planet>>> {
        return flow {
            while(true) {
                try {
                    emit(ApiResult.Loading)
                    emit(ApiResult.Success(planetDataSource.retrieveAll()))
                } catch(ex: Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(Constants.RefreshDelay.PLANETS_REFRESH)
            }

        }.flowOn(Dispatchers.IO)
    }


}