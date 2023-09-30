package com.sheinahamisi.weathersagepro.data.util

import com.sheinahamisi.weathersagepro.core.util.Resource
import com.sheinahamisi.weathersagepro.core.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): Flow<Resource<T>> {
    return flow {
        try {
            emit(Resource.Loading())
            val response = apiCall()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.DynamicString(e.message ?: "An unexpected error occurred")))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    UiText.DynamicString(
                        "Couldn't reach server. Check your internet connection!"
                    )
                )
            )
        }
    }
}