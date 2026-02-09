package com.rangga.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET


interface ApiService {
    @GET("quotes/random") // Endpoint untuk kutipan acak
    fun getRandomQuote(): Call<QuoteResponse>
    
    @GET
    fun getIndonesianQuote(@retrofit2.http.Url url: String): Call<IndoQuoteResponse>

}

object ApiClient {
    private const val BASE_URL = "https://dummyjson.com/" // Update URL Dasar

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}