package com.saiful.newsapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.saiful.newsapp.database.NewsDatabase
import com.saiful.newsapp.model.NewsArticle
import com.saiful.newsapp.network.MarsApi
import com.saiful.newsapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel (application: Application): AndroidViewModel(application) {
    val repository: NewsRepository
    val result = mutableListOf<NewsArticle>()

    init {
        val newsDao = NewsDatabase.getDatabase(application).getNews()
        repository = NewsRepository(newsDao)
        getTopHeadlines()
        addNews(result.toList())
    }

    fun addNews(newsArticle: List<NewsArticle>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNews(result)
        }
    }

    fun getTopHeadlines() {
        viewModelScope.launch {
            try {
                val response = MarsApi.retrofitService.topHeadlines()
                response.articles.map {
                    result.add(NewsArticle(
                        0,
                        it.title,
                        it.author,
                        it.content,
                        it.description,
                        it.publishedAt,
                        it.source?.name,
                        it.url,
                        it.urlToImage
                    ))
                }
                Log.d("TAG", "getTopHeadlines: ${result.size}")
//                loadNews()
            } catch (e: Exception) {
                Log.d("TAG", "$e")
            }
        }
    }

    private fun loadNews() {

//            addNews(result[0])

    }
}