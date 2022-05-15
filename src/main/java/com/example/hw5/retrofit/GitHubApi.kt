package com.example.hw5.retrofit

import com.example.hw5.model.GithubUser
import com.example.hw5.model.GithubUserDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): List<GithubUser>

    @GET("user/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): GithubUserDetails
}