package com.adnroid.movieapplication.data.repository

import androidx.paging.*
import com.adnroid.movieapplication.data.mapper.MovieMapper
import com.adnroid.movieapplication.data.network.ApiInterface
import com.adnroid.movieapplication.domain.Results

class MoviePagingSource(private val apiInterface: ApiInterface,
                        private val mapper: MovieMapper
) :
    PagingSource<Int, Results>() {

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        try {
            val pageIndex = params.key ?: 1
            val response = apiInterface.getPopularMovie(
                apiKey = "bd8c2d1b4af41b4060929a043be50eb6",
                language = "ru-RU",
                page = pageIndex
            )
            val data = response.results.map { mapper.mapResultsDtoToResults(it) }

            return LoadResult.Page(
                data = data,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (data.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}