package com.adnroid.movieapplication.domain

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.liveData
import androidx.paging.cachedIn
import com.adnroid.movieapplication.data.repository.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend fun getPopularMovie(viewModelScope: CoroutineScope) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = repository.getPopularMovieList(viewModelScope = viewModelScope)
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