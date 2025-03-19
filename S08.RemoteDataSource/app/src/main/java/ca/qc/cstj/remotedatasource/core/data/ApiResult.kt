package ca.qc.cstj.remotedatasource.core.data

import ca.qc.cstj.remotedatasource.model.Planet

sealed interface ListPlanetApiResult {
    data object Loading : ListPlanetApiResult
    data class Success(val planets: List<Planet>) : ListPlanetApiResult
    data class Error(val exception: Exception, val message: String = "") :ListPlanetApiResult
}