package ca.qc.cstj.remotedatasource.data.repositories

import android.util.Log
import ca.qc.cstj.remotedatasource.core.Constants
import ca.qc.cstj.remotedatasource.core.data.ListPlanetApiResult
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

    fun retrieveAll() : Flow<ListPlanetApiResult> {

        return flow {
            while(true) {
                try {
                    emit(ListPlanetApiResult.Loading)
                    emit(ListPlanetApiResult.Success(_planetDataSource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(ListPlanetApiResult.Error(ex, "Erreur lors de la réception des planètes"))
                }
                delay(Constants.RefreshDelays.PLANETS_REFRESH_TIMER)
            }

        }.flowOn(Dispatchers.IO)

    }

    fun retrieveOne()  {

    }

}