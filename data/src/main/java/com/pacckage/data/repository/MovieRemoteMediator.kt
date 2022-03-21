package com.pacckage.data.repository

import androidx.paging.*
import androidx.room.withTransaction
import com.pacckage.data.local_db.MovieDataBase
import com.pacckage.data.local_db.model.RemoteKeys
import com.pacckage.data.local_db.model.ResultsEntity
import com.pacckage.data.mapper.MovieMapper
import com.pacckage.data.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator constructor(
    private val apiInterface: ApiInterface,
    private val mapper: MovieMapper,
    private val db: MovieDataBase
) : RemoteMediator<Int, ResultsEntity>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultsEntity>
    ): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val data = apiInterface.getPopularMovie(
                apiKey = "bd8c2d1b4af41b4060929a043be50eb6",
                language = "ru-RU",
                page = STARTING_PAGE_INDEX
            )
            val response = data.results.map { mapper.mapResultsDtoToResultsEntity(it) }
            val endOfList = response.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeyDao().clearAll()
                    db.movieDao().clearAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfList) null else page + 1
                val keys = response.map {
                    RemoteKeys(it.id, prevKey, nextKey)
                }
                db.remoteKeyDao().insertRemote(keys)
                db.movieDao().insertPopularMovie(response)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfList)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, ResultsEntity>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRefreshRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey ?: MediatorResult.Success(
                    endOfPaginationReached = true
                )
                nextKey
            }
        }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, ResultsEntity>): RemoteKeys? {
        return withContext(Dispatchers.IO) {
            state.pages
                .firstOrNull { it.data.isNotEmpty() }
                ?.data?.firstOrNull()
                ?.let { movie -> db.remoteKeyDao().getRemoteKeys(movie.id) }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, ResultsEntity>): RemoteKeys? {
        return withContext(Dispatchers.IO) {
            state.pages
                .lastOrNull { it.data.isNotEmpty() }
                ?.data?.lastOrNull()
                ?.let { movie -> db.remoteKeyDao().getRemoteKeys(movie.id) }
        }
    }

    private suspend fun getRefreshRemoteKey(state: PagingState<Int, ResultsEntity>): RemoteKeys? {
        return withContext(Dispatchers.IO) {
            state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.let {
                    db.remoteKeyDao().getRemoteKeys(it.id)
                }
            }
        }
    }


}