package com.adnroid.movieapplication.domain

import android.util.Log
import androidx.lifecycle.liveData
import com.adnroid.movieapplication.data.repository.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend fun getPopularMovie(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = repository.getPopularMovie(page)
            emit(Resource.success(data = request))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
            Log.e(
                "Error",
                "Settings: " + Resource.error(data = null, message = e.message ?: "Error")
            )
        }
    }

}