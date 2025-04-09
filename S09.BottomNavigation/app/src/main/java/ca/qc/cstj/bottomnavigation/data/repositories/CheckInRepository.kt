package ca.qc.cstj.bottomnavigation.data.repositories

import ca.qc.cstj.bottomnavigation.core.data.ApiResult
import ca.qc.cstj.bottomnavigation.data.datasources.CheckInDataSource
import ca.qc.cstj.bottomnavigation.model.CheckIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CheckInRepository {

    private val checkInDataSource = CheckInDataSource()

    fun retrieveAll() : Flow<ApiResult<List<CheckIn>>> {
        return flow {
            try {
                emit(ApiResult.Success(checkInDataSource.retrieveAll()))
            } catch(ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun create(checkIn: CheckIn) :Flow<ApiResult<CheckIn>> {
        return flow {
            try {
                emit(ApiResult.Success(checkInDataSource.create(checkIn)))
            } catch(ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

}