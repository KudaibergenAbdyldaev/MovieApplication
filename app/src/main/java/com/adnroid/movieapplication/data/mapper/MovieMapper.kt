package com.adnroid.movieapplication.data.mapper

import com.adnroid.movieapplication.data.network.model.MovieDto
import com.adnroid.movieapplication.data.network.model.ResultsDto
import com.adnroid.movieapplication.domain.Movie
import com.adnroid.movieapplication.domain.Results

class MovieMapper {

    fun mapResultsDtoToResults(dto: ResultsDto): Results {
        return Results(dto.img, dto.id, dto.title, dto.overview)
    }

    fun mapMovieDtoToMovie(dto: MovieDto): Movie {
        return Movie(dto.page, dto.results.map { mapResultsDtoToResults(it) })
    }

}