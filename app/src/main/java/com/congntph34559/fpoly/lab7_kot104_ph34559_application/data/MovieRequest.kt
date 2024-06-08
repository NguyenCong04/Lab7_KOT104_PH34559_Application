package com.congntph34559.fpoly.lab7_kot104_ph34559_application.data

data class MovieRequest(
    val filmId: Int? = null,
    val filmName: String,
    val duration: Int,
    val releaseDate: String,
    val genre: String,
    val national: String,
    val description: String,
    val image: String
)
data class StatusResponse(
    val status: Int,
    val message: String
)