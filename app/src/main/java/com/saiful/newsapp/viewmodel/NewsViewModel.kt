package com.saiful.newsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.saiful.newsapp.R
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDatabase
import com.saiful.newsapp.global.Global
import com.saiful.newsapp.network.NewsApi
import com.saiful.newsapp.repository.NewsRepository
import com.saiful.newsapp.utils.Internet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NewsRepository
    private val result = mutableListOf<NewsArticle>()
    lateinit var readAllNews: LiveData<List<NewsArticle>>

    init {
        val newsDao = NewsDatabase.getDatabase(application).getNewsDao()
        repository = NewsRepository(newsDao)
        readAllNewsFromLocal()
    }

    fun readAllNewsFromLocal() {
        with(repository) {
//            Log.d("TAG", "readAllNewsFromLocal: ${Global.category}")
            readAllNews = when (Global.category) {
                "business" -> readAllNews("business")
                "entertainment" -> readAllNews("entertainment")
                "general" -> readAllNews("general")
                "health" -> readAllNews("health")
                "science" -> readAllNews("science")
                "sports" -> readAllNews("sports")
                else -> readAllNews("technology")
            }
        }
    }

    fun loadNewsFromRemote() {
        if (Internet.isOnline(getApplication())) {
//            Log.d("TAG", "loadNewsFromRemote: call news api")
            viewModelScope.launch {
                try {
                    val response = NewsApi.retrofitService.topHeadlinesNews(
                        Global.category!!,
                        getApplication<Application>().resources.getString(R.string.api_key)
                    )
                    response.articles.map {
                        result.add(
                            NewsArticle(
                                0,
                                it.title,
                                it.author,
                                it.content,
                                it.description,
                                it.publishedAt,
                                it.source?.name,
                                it.url,
                                it.urlToImage,
                                Global.category
                            )
                        )
                    }
                    addNews()
//                Log.d("TAG", "getTopHeadlines: called ${response.articles.size}")
                } catch (e: Exception) {
                    Log.d("TAG", "$e")
                }
            }
        }
    }

    private fun addNews() {
//        Log.d("TAG", "addNews: result${result.size}")
        for (i in result) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addNews(i)
            }
        }
        result.clear()
//        Log.d("TAG", "addNews: result${result.size}")
    }

    fun addBookmarkNews(newsArticle: NewsArticle) {
//        Log.d("TAG", "addBookmarkNews: ${newsArticle.id}")
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookmarkNews(
                newsArticle.id, !newsArticle.isBookmark
            )
        }
    }

    fun loadBookmarkNews() {
        readAllNews = repository.readAllBookmarkNews()
    }
}