package ca.qc.cstj.remotedatasource.data.repositories

import android.util.Log
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource
import ca.qc.cstj.remotedatasource.model.Planet

//Faire la mise en continu à un interval
//Faire du traitement de données
//Appel plusieurs routes et fusionne les données

//L'appel au service est fait par le DataSource
class PlanetRepository {

    private val _planetDataSource = PlanetDataSource()

    fun retrieveAll() : List<Planet> {
        try {
            //run démarre une coroutine,car l'appel au serveur doit être fait de manière asynchrone
            _planetDataSource.retrieveAll().run {
                Log.d("PLANETS", this.toString())
                return this
            }
        } catch (ex: Exception) {
            Log.d("EXCEPTION", ex.toString())
        }
        return listOf()
    }

    fun retrieveOne()  {

    }

}