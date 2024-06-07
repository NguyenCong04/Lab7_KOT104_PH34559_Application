package com.congntph34559.fpoly.lab7_kot104_ph34559_application.data

import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("/api/Film")
    suspend fun getListFilms(): Response<List<MovieResponse>>
}