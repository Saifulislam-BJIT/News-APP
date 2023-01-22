package com.saiful.newsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDao

class NewsRepository(private val newsDao: NewsDao) {

    fun readAllNews(category: String) = newsDao.readAllNews(category)

    fun searchNews(search: String): LiveData<List<NewsArticle>> {
        val result = newsDao.searchNews(search)
        Log.d("TAG", "searchNews: ${result.value?.size} $search")
        return result
    }

    suspend fun addNews(newsArticle: NewsArticle) {
        newsDao.addNews(newsArticle)
    }

    suspend fun addBookmarkNews(newsArticle: NewsArticle) {
        newsDao.addBookmarkNews(newsArticle)
    }

    fun readAllBookmarkNews() = newsDao.readAllBookmarkNews()

}