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
    var readAllNews: LiveData<List<NewsArticle>>

    init {
        val newsDao = NewsDatabase.getDatabase(application).getNewsDao()
        repository = NewsRepository(newsDao)
//        readAllNews()
        readAllNews = repository.readAllNews
        Log.d("TAG", repository.readAllNews.value?.size.toString())
    }

    fun getTopHeadlines() {
        viewModelScope.launch {
            try {
                val response = NewsApi.retrofitService.topHeadlines()
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
//                Log.d("TAG", "getTopHeadlines: called ${result}")
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
//        Log.d("TAG", "addNews: called ")
    }
}