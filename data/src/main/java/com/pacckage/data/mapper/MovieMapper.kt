package com.pacckage.data.mapper

import com.pacckage.data.network.model.MovieDto
import com.pacckage.data.network.model.ResultsDto
import com.pacckage.domain.Movie
import com.pacckage.domain.Results

class MovieMapper {

    fun mapResultsDtoToResults(dto: ResultsDto): Results {
        return Results("https://image.tmdb.org/t/p/w500/${dto.img}", dto.id, dto.title, dto.overview)
    }

    fun mapMovieDtoToMovie(dto: MovieDto): Movie {
        return Movie(dto.page, dto.results.map { mapResultsDtoToResults(it) })
    }

}