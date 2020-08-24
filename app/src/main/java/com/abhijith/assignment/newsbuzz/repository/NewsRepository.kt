package com.abhijith.assignment.newsbuzz.repository

import com.abhijith.assignment.newsbuzz.api.RetrofitInstance
import com.abhijith.assignment.newsbuzz.data.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(queryString: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(queryString,pageNumber)
}