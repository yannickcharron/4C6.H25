package ca.qc.cstj.bottomnavigation.core.data

sealed class ApiResult<out T> {
    data object Loading: ApiResult<Nothing>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception):ApiResult<Nothing>()
}

