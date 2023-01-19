package com.saiful.newsapp.repository

import androidx.lifecycle.LiveData
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDao

class NewsRepository(private val newsDao: NewsDao) {
    var readAllNews: LiveData<List<NewsArticle>> = newsDao.readAllNews()
//private val db: NewsDao = DataBase.getDatabase(context).newsDao()

//    suspend fun readAllNews(): LiveData<List<NewsArticle>> {
//        return newsDao.readAllNews()
//    }
    suspend fun addNews(newsArticle: NewsArticle) {
        newsDao.addNews(newsArticle)
    }

//    fun getAllNews(): LiveData<List<NewsArticle>> {
//        return db.getAll()
//    }
}