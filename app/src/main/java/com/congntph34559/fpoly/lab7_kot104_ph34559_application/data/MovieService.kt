package com.congntph34559.fpoly.lab7_kot104_ph34559_application.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/api/Film")
    suspend fun getListFilms(): Response<List<MovieResponse>>

    @GET("/api/Film/{id}")
    suspend fun getFilmDetail(@Path("id") id: String): Response<MovieResponse>

    @POST("/api/Film")
    suspend fun addFilm(@Body filmRequest: MovieRequest): Response<StatusResponse>

    @PUT("/api/Film/{id}")
    suspend fun updateFilm(
        @Path("id") id: String,
        @Body filmRequest: MovieRequest
    ): Response<StatusResponse>
    @DELETE("/api/Film/{id}")
    suspend fun deleteFilm(@Path("id") id: String): Response<StatusResponse>

}