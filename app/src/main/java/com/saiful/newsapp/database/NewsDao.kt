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

    @Query("SELECT * FROM news ORDER BY id DESC")
    fun readAllNews(): LiveData<List<NewsArticle>>

}