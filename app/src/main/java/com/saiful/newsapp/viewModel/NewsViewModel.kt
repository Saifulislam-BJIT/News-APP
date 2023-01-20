package com.saiful.newsapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.database.NewsDatabase
import com.saiful.newsapp.global.Global
import com.saiful.newsapp.network.NewsApi
import com.saiful.newsapp.repository.NewsRepository
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
            Log.d("TAG", "readAllNewsFromLocal: ${Global.category}")
            readAllNews = when (Global.category) {
                "business" -> readAllBusinessNews()
                "entertainment" -> readAllEntertainmentNews()
                "general" -> readAllGeneralNews()
                "health" -> readAllHealthNews()
                "science" -> readAllScienceNews()
                "sports" -> readAllSportsNews()
                else -> readAllTechnologyNews()
            }
        }
    }

    fun loadNewsFromRemote() {
        viewModelScope.launch {
            try {
                val response = when (Global.category) {
                    "business" -> NewsApi.retrofitService.topHeadlinesBusiness()
                    "entertainment" -> NewsApi.retrofitService.topHeadlinesEntertainment()
                    "general" -> NewsApi.retrofitService.topHeadlinesGeneral()
                    "health" -> NewsApi.retrofitService.topHeadlinesHealth()
                    "science" -> NewsApi.retrofitService.topHeadlinesScience()
                    "sports" -> NewsApi.retrofitService.topHeadlinesSports()
                    else -> NewsApi.retrofitService.topHeadlinesTechnology()
                }
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
                Log.d("TAG", "getTopHeadlines: called $result")
            } catch (e: Exception) {
                Log.d("TAG", "$e")
            }
        }
    }

    private fun addNews() {
        for (i in result) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addNews(i)
            }
        }
        result.clear()
    }

    fun addBookmarkNews(newsArticle: NewsArticle) {
        Log.d("TAG", "addBookmarkNews: ${newsArticle.id}")
        viewModelScope.launch(Dispatchers.IO)  {
            repository.addBookmarkNews(NewsArticle(
                newsArticle.id,
                newsArticle.title,
                newsArticle.author,
                newsArticle.content,
                newsArticle.description,
                newsArticle.publishedAt,
                newsArticle.sourceName,
                newsArticle.url,
                newsArticle.urlToImage,
                newsArticle.category,
                !newsArticle.isBookmark
            ))
        }
    }

    fun loadBookmarkNews() {
        readAllNews = repository.readAllBookmarkNews()
    }

    fun searchNews(query: String) {
        readAllNews = repository.searchNews(query)
        Log.d("TAG", "searchNews: ${repository.searchNews(query).value?.size}")
    }
}