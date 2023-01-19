package com.saiful.newsapp.repository

import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDao

class NewsRepository(private val newsDao: NewsDao) {
    fun readAllNews() = newsDao.readAllNews()

    suspend fun addNews(newsArticle: NewsArticle) {
        newsDao.addNews(newsArticle)
    }

}