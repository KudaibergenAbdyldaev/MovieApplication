package com.adnroid.movieapplication.data.mapper

import com.adnroid.movieapplication.data.network.model.MovieDto
import com.adnroid.movieapplication.data.network.model.ResultsDto
import com.adnroid.movieapplication.domain.Movie
import com.adnroid.movieapplication.domain.Results

class MovieMapper {

    fun mapDtoToModel(dto: MovieDto) = Movie(
        page = dto.page,
        results = mapResultsDtoToResults(dto.results)
    )

    private fun mapResultsDtoToResults(results: ResultsDto): Results{
        return Results(results.img, results.id, results.title, results.overview)
    }

}