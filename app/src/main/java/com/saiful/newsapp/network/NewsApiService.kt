package com.saiful.newsapp.network

import com.saiful.newsapp.model.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://newsapi.org/v2/"
private const val TOKEN = "9d68e0d1f113454cb2e1e6ad604ac096"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object NewsApi {
    val retrofitService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}

interface NewsApiService {
    @GET("top-headlines?country=us&category=business&apiKey=$TOKEN")
    suspend fun topHeadlinesBusiness() : News

    @GET("top-headlines?country=us&category=entertainment&apiKey=$TOKEN")
    suspend fun topHeadlinesEntertainment() : News

    @GET("top-headlines?country=us&category=general&apiKey=$TOKEN")
    suspend fun topHeadlinesGeneral() : News

    @GET("top-headlines?country=us&category=health&apiKey=$TOKEN")
    suspend fun topHeadlinesHealth() : News

    @GET("top-headlines?country=us&category=science&apiKey=$TOKEN")
    suspend fun topHeadlinesScience() : News

    @GET("top-headlines?country=us&category=sports&apiKey=$TOKEN")
    suspend fun topHeadlinesSports() : News

    @GET("top-headlines?country=us&category=technology&apiKey=$TOKEN")
    suspend fun topHeadlinesTechnology() : News

}