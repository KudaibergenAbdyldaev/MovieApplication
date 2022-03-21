package com.pacckage.data.mapper

import com.pacckage.data.local_db.model.ResultsEntity
import com.pacckage.data.network.model.DetailMovieDto
import com.pacckage.data.network.model.MovieDto
import com.pacckage.data.network.model.ResultsDto
import com.pacckage.domain.DetailMovie
import com.pacckage.domain.Movie
import com.pacckage.domain.Results

class MovieMapper {

    companion object {
        const val IMAGE_LINK = "https://image.tmdb.org/t/p/w500/"
    }

    fun mapResultsDtoToResults(dto: ResultsDto): Results {
        return Results("$IMAGE_LINK${dto.img}", dto.id)
    }

    fun mapMovieDtoToMovie(dto: MovieDto): Movie {
        return Movie(dto.page, dto.results.map { mapResultsDtoToResults(it) })
    }

    fun mapDetailDtoToDetailMovie(dto: DetailMovieDto): DetailMovie {

        return DetailMovie(dto.title, dto.overview, IMAGE_LINK+dto.posterPath)
    }

    fun mapResultsDtoToResultsEntity(dto: ResultsDto): ResultsEntity {
        return ResultsEntity("0","$IMAGE_LINK${dto.img}", dto.id)
    }

    fun mapResultsEntityToResults(dto: ResultsEntity): Results {
        return Results("$IMAGE_LINK${dto.img}",dto.movieId)
    }



}