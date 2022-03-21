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
    private val db: MovieDataBase,
) : RemoteMediator<Int, ResultsEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, ResultsEntity>
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
            val response = apiInterface.getPopularMovie(
                page = page,
                apiKey = "bd8c2d1b4af41b4060929a043be50eb6",
                language = "ru-RU"
            ).results
            val isEndOfList = response.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.movieDao().clearAll()
                    db.remoteKeyDao().clearAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.map {
                    RemoteKeys(it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                db.remoteKeyDao().insertRemote(keys)

                db.movieDao()
                    .insertPopularMovie(response.map {
                        mapper.mapResultsDtoToResultsEntity(
                            it,
                            it.id.toString()
                        )
                    })
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, ResultsEntity>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ResultsEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                db.remoteKeyDao().getRemoteKeys(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, ResultsEntity>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> db.remoteKeyDao().getRemoteKeys(movie.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, ResultsEntity>): RemoteKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> db.remoteKeyDao().getRemoteKeys(movie.id) }
    }
}