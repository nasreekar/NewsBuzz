package com.abhijith.assignment.newsbuzz.api

import com.abhijith.assignment.newsbuzz.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        // lazy - we only initialise the piece of code within the curly braces only once
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            //pass the client to retrofit instance
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // how the response to be interpreted and converted to kotlin object
                .client(client)
                .build()
        }

        // get api instance from retrofit builder
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}