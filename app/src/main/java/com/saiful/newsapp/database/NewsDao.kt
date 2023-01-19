package com.saiful.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(newsArticle: NewsArticle)

    @Query("SELECT * FROM news WHERE category='business' ORDER BY id DESC")
    fun readAllBusinessNews(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM news WHERE category='entertainment' ORDER BY id DESC")
    fun readAllEntertainmentNews(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM news WHERE category='general' ORDER BY id DESC")
    fun readAllGeneralNews(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM news WHERE category='health' ORDER BY id DESC")
    fun readAllHealthNews(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM news WHERE category='science' ORDER BY id DESC")
    fun readAllScienceNews(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM news WHERE category='sports' ORDER BY id DESC")
    fun readAllSportsNews(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM news WHERE category='technology' ORDER BY id DESC")
    fun readAllTechnologyNews(): LiveData<List<NewsArticle>>

    // Search function
    @Query("SELECT * FROM news WHERE title LIKE '%' || :search || '%'")
    fun searchNews(search: String?): LiveData<List<NewsArticle>>

}