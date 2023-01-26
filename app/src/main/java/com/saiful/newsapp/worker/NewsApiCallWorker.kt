package com.saiful.newsapp.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.saiful.newsapp.Constant.Constant
import com.saiful.newsapp.Constant.Constant.Companion.TOKEN
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDatabase
import com.saiful.newsapp.network.NewsApi
import com.saiful.newsapp.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsApiCallWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
//        Log.d("TAG", "readFromNews: work manager doWork")
        try {
            val scope = CoroutineScope(Dispatchers.IO)
//        Business
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "business", TOKEN
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Constant.context!!).getNewsDao()
                    val repository = NewsRepository(newsDao)
                    repository.addNews(
                        NewsArticle(
                            0,
                            i.title,
                            i.author,
                            i.content,
                            i.description,
                            i.publishedAt,
                            i.source?.name,
                            i.url,
                            i.urlToImage,
                            "business",
                            false
                        )
                    )
                }
            }

            //        entertainment
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "entertainment", TOKEN
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Constant.context!!).getNewsDao()
                    val repository = NewsRepository(newsDao)
                    repository.addNews(
                        NewsArticle(
                            0,
                            i.title,
                            i.author,
                            i.content,
                            i.description,
                            i.publishedAt,
                            i.source?.name,
                            i.url,
                            i.urlToImage,
                            "entertainment",
                            false
                        )
                    )
                }
            }

            //        general
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "general", TOKEN
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Constant.context!!).getNewsDao()
                    val repository = NewsRepository(newsDao)
                    repository.addNews(
                        NewsArticle(
                            0,
                            i.title,
                            i.author,
                            i.content,
                            i.description,
                            i.publishedAt,
                            i.source?.name,
                            i.url,
                            i.urlToImage,
                            "general",
                            false
                        )
                    )
                }
            }

            //        health
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "health", TOKEN
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Constant.context!!).getNewsDao()
                    val repository = NewsRepository(newsDao)
                    repository.addNews(
                        NewsArticle(
                            0,
                            i.title,
                            i.author,
                            i.content,
                            i.description,
                            i.publishedAt,
                            i.source?.name,
                            i.url,
                            i.urlToImage,
                            "health",
                            false
                        )
                    )
                }
            }

            //        science
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "science", TOKEN
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Constant.context!!).getNewsDao()
                    val repository = NewsRepository(newsDao)
                    repository.addNews(
                        NewsArticle(
                            0,
                            i.title,
                            i.author,
                            i.content,
                            i.description,
                            i.publishedAt,
                            i.source?.name,
                            i.url,
                            i.urlToImage,
                            "science",
                            false
                        )
                    )
                }
            }

            //        sports
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "sports", TOKEN
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Constant.context!!).getNewsDao()
                    val repository = NewsRepository(newsDao)
                    repository.addNews(
                        NewsArticle(
                            0,
                            i.title,
                            i.author,
                            i.content,
                            i.description,
                            i.publishedAt,
                            i.source?.name,
                            i.url,
                            i.urlToImage,
                            "sports",
                            false
                        )
                    )
                }
            }

            //        technology
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "technology", TOKEN
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Constant.context!!).getNewsDao()
                    val repository = NewsRepository(newsDao)
                    repository.addNews(
                        NewsArticle(
                            0,
                            i.title,
                            i.author,
                            i.content,
                            i.description,
                            i.publishedAt,
                            i.source?.name,
                            i.url,
                            i.urlToImage,
                            "technology",
                            false
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("TAG", "$e")
        }
        return Result.success()
    }
}