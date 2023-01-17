package com.saiful.newsapp.repository

import com.saiful.newsapp.database.NewsDao
import com.saiful.newsapp.model.NewsArticle

class NewsRepository(private val newsDao: NewsDao) {

    suspend fun addNews(newsArticle: List<NewsArticle>) {
        newsArticle.map {
            newsDao.addNews(it)
        }
    }
}