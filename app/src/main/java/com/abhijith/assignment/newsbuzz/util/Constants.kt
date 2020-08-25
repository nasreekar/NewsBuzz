package com.abhijith.assignment.newsbuzz.util

class Constants {
    // using companion object so that we dont have to create an instance of that class
    companion object {
        const val API_KEY = "" //should replace with your own
        const val BASE_URL = "https://newsapi.org"
        const val SEARCH_NEWS_TIME_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20
    }
}