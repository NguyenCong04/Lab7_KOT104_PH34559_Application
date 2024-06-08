package com.congntph34559.fpoly.lab7_kot104_ph34559_application.data

import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.Movie
import com.congntph34559.fpoly.lab7_kot104_ph34559_application.ui.screens.MovieFormData


fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = this.filmId,
        filmName = this.filmName,
        duration = this.duration,
        releaseDate = this.releaseDate,
        genre = this.genre,
        national = this.national,
        description = this.description,
        image = this.image
    )
}
fun MovieFormData.toMovieRequest(): MovieRequest {
    val filmIdInt = try {
        this.id?.toIntOrNull()
    } catch (e: NumberFormatException) {
        null
    }
    val durationInt = try {
        this.duration.toInt()
    } catch (e: NumberFormatException) {
        0
    }
    return MovieRequest(
        filmId = filmIdInt,
        filmName = this.filmName,
        duration = durationInt,
        releaseDate = this.releaseDate,
        genre = this.genre,
        national = this.national,
        description = this.description,
        image = this.imageUrl
    )
}
fun Movie?.toMovieFormData() = this?.let {
    MovieFormData(
        this.id,
        this.filmName,
        this.duration,
        this.releaseDate,
        this.genre,
        this.national,
        this.description,
        this.image
    )
}



//fun MovieResponse.toMovie(): Movie {
//    return Movie(
//        id = this.filmId,
//        title = this.filmName,
//        duration = this.duration,
//        releaseDate = this.releaseDate,
//        genre = this.genre,
//        shotDescription = this.description,
//        posterUrl = this.image
//    )
//}