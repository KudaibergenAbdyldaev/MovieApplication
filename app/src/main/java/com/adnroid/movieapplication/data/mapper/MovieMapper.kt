package com.adnroid.movieapplication.data.mapper

import com.adnroid.movieapplication.data.network.model.MovieDto
import com.adnroid.movieapplication.data.network.model.ResultsDto
import com.adnroid.movieapplication.domain.Movie
import com.adnroid.movieapplication.domain.Results
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun mapDtoToModel(dto: MovieDto) = Movie(
        page = dto.page,
        results = mapResultsDtoToResults(dto.results)
    )

    private fun mapResultsDtoToResults(results: List<ResultsDto>): List<Results>{

        val list = ArrayList<Results>()
        for (i in results){
            list.add(Results(i.img, i.id, i.title, i.overview))
        }

        return list
    }

}