package com.example.ozinshe.data

import com.example.ozinshe.domain.model.FavoriteMoviesResponse
import com.example.ozinshe.domain.model.LoginRequest
import com.example.ozinshe.domain.model.LoginResponse
import com.example.ozinshe.domain.model.MainMoviesResponse
import com.example.ozinshe.domain.model.MovieByIdResponse
import com.example.ozinshe.domain.model.MovieIdModel
import com.example.ozinshe.domain.model.MoviesByCategoryMainModel
import com.example.ozinshe.domain.model.RegistrationRequest
import com.example.ozinshe.domain.model.RegistrationResponse
import com.example.ozinshe.domain.model.ResetPasswordRequest
import com.example.ozinshe.domain.model.ResetPasswordResponse
import com.example.ozinshe.domain.model.SeriesListResponseItem
import com.example.ozinshe.domain.model.UserInfoRequest
import com.example.ozinshe.domain.model.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun registrationUser(@Body registrationRequest: RegistrationRequest): RegistrationResponse

    @GET("/core/V1/movies_main")
    suspend fun getMainMovies(@Header("Authorization") token: String): MainMoviesResponse

    @GET("/core/V1/movies/main")
    suspend fun getMoviesByCategory(@Header("Authorization") token: String): MoviesByCategoryMainModel

    @GET("/core/V1/movies/{id}")
    suspend fun getMovieById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): MovieByIdResponse

    @GET("/core/V1/seasons/{id}")
    suspend fun getSeries(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): List<SeriesListResponseItem>

    @POST("/core/V1/favorite")
    suspend fun addFavourite(
        @Header("Authorization") token: String,
        @Body movieBody: MovieIdModel
    ): MovieIdModel

    @HTTP(method = "DELETE", path = "/core/V1/favorite/", hasBody = true)
    suspend fun deleteFavourite(
        @Header("Authorization") token: String,
        @Body movieBody: MovieIdModel
    ): Unit

    @GET("/core/V1/favorite/")
    suspend fun getFavourite(@Header("Authorization") token: String): FavoriteMoviesResponse

    @GET("/core/V1/user/profile")
    suspend fun getUserInfo(@Header("Authorization") token: String): UserInfoResponse

    @PUT("/core/V1/user/profile/")
    suspend fun postUserInfo(
        @Header("Authorization") token: String,
        @Body request: UserInfoRequest
    ): UserInfoResponse

    @PUT("/core/V1/user/profile/changePassword")
    suspend fun resetPassword(
        @Header("Authorization") token: String,
        @Body request: ResetPasswordRequest
    ): ResetPasswordResponse
}
