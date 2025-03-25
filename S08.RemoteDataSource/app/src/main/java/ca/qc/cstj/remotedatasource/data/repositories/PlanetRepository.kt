package ca.qc.cstj.remotedatasource.data.repositories

import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.core.data.ApiResult
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.model.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//Faire la mise en continu à un interval
//Faire du traitement de données
//Appel plusieurs routes et fusionne les données

//L'appel au service est fait par le DataSource
class PlanetRepository {

    private val _planetDataSource = PlanetDataSource()

    fun retrieveAll() : Flow<ApiResult<List<Planet>>> {

        return flow {
            while(true) {
                try {
                    emit(ApiResult.Loading)
                    emit(ApiResult.Success(_planetDataSource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(ApiResult.Error(ex, "Erreur lors de la réception des planètes"))
                }
                delay(Constants.RefreshDelays.PLANETS_REFRESH_TIMER)
            }

        }.flowOn(Dispatchers.IO)

    }

    fun retrieveOne(href: String) : Flow<ApiResult<Planet>> {

        return flow {
            while(true) {
                try {
                    emit(ApiResult.Loading)
                    emit(ApiResult.Success(_planetDataSource.retrieveOne(href)))
                } catch(ex: Exception) {
                    emit(ApiResult.Error(ex, "Erreur lors de la réception de la planète"))
                }
                delay(Constants.RefreshDelays.PLANETS_REFRESH_TIMER)
            }
        }.flowOn(Dispatchers.IO)
    }

}