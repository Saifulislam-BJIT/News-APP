package com.saiful.newsapp.repository

import androidx.lifecycle.LiveData
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDao

class NewsRepository(private val newsDao: NewsDao) {
    val readAllNews: LiveData<List<NewsArticle>> = newsDao.readAllNews()

    suspend fun addNews(newsArticle: NewsArticle) {
        newsDao.addNews(newsArticle)
    }
}