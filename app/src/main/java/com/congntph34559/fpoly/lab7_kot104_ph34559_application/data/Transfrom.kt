package com.congntph34559.fpoly.lab7_kot104_ph34559_application.data

import com.congntph34559.fpoly.lab7_kot104_ph34559_application.model.Movie

fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = this.filmId,
        title = this.filmName,
        duration = this.duration,
        releaseDate = this.releaseDate,
        genre = this.genre,
        shotDescription = this.description,
        posterUrl = this.image
    )
}