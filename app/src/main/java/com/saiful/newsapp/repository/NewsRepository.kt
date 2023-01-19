package com.saiful.newsapp.repository

import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDao

class NewsRepository(private val newsDao: NewsDao) {
    fun readAllBusinessNews() = newsDao.readAllBusinessNews()

    fun readAllEntertainmentNews() = newsDao.readAllEntertainmentNews()

    fun readAllGeneralNews() = newsDao.readAllGeneralNews()

    fun readAllHealthNews() = newsDao.readAllHealthNews()

    fun readAllScienceNews() = newsDao.readAllScienceNews()

    fun readAllSportsNews() = newsDao.readAllSportsNews()

    fun readAllTechnologyNews() = newsDao.readAllTechnologyNews()

    suspend fun addNews(newsArticle: NewsArticle) {
        newsDao.addNews(newsArticle)
    }

}