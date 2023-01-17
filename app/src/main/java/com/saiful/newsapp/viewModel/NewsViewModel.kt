package com.saiful.newsapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiful.newsapp.model.NewsArticle
import com.saiful.newsapp.network.MarsApi
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {
    fun getTopHeadlines() {
        viewModelScope.launch {
            try {
                val response = MarsApi.retrofitService.topHeadlines()
                val result = mutableListOf<NewsArticle>()
                response.articles.map {
                    result.add(NewsArticle(
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
            } catch (e: Exception) {
                Log.d("TAG", "$e")
            }
        }
    }
}