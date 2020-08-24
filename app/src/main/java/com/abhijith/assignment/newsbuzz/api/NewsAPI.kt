package com.abhijith.assignment.newsbuzz.api

import com.abhijith.assignment.newsbuzz.models.NewsResponse
import com.abhijith.assignment.newsbuzz.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    // we need to call this function asynchronously so we use coroutines, suspend function
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "in", //default value set to India

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}