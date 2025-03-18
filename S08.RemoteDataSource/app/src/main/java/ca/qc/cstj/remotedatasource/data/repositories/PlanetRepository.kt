package ca.qc.cstj.remotedatasource.data.repositories

import android.util.Log
import ca.qc.cstj.remotedatasource.data.datasources.PlanetDataSource

//Faire la mise en continu à un interval
//Faire du traitement de données
//Appel plusieurs routes et fusionne les données

//L'appel au service est fait par le DataSource
class PlanetRepository {

    private val _planetDataSource = PlanetDataSource()

    fun retrieveAll() {
        try {
            //run démarre une coroutine,car l'appel au serveur doit être fait de manière asynchrone
            _planetDataSource.retrieveAll().run {
                Log.d("PLANETS", this.toString())
            }
        } catch (ex: Exception) {
            Log.d("EXCEPTION", ex.toString())
        }
    }

    fun retrieveOne()  {

    }

}