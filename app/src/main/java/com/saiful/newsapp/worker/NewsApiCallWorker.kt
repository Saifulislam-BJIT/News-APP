package com.saiful.newsapp.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDatabase
import com.saiful.newsapp.global.Global
import com.saiful.newsapp.network.NewsApi
import com.saiful.newsapp.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsApiCallWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("TAG", "readFromNews: work manager doWork")
        try {
            val scope = CoroutineScope(Dispatchers.IO)
//        Business
            scope.launch {
                val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                    "business",
                    "9d68e0d1f113454cb2e1e6ad604ac096"
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Global.context!!).getNewsDao()
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
                    "entertainment",
                    "9d68e0d1f113454cb2e1e6ad604ac096"
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Global.context!!).getNewsDao()
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
                    "general",
                    "9d68e0d1f113454cb2e1e6ad604ac096"
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Global.context!!).getNewsDao()
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
                    "health",
                    "9d68e0d1f113454cb2e1e6ad604ac096"
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Global.context!!).getNewsDao()
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
                    "science",
                    "9d68e0d1f113454cb2e1e6ad604ac096"
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Global.context!!).getNewsDao()
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
                    "sports",
                    "9d68e0d1f113454cb2e1e6ad604ac096"
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Global.context!!).getNewsDao()
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
                    "technology",
                    "9d68e0d1f113454cb2e1e6ad604ac096"
                )
                for (i in response.articles) {
                    val newsDao = NewsDatabase.getDatabase(Global.context!!).getNewsDao()
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