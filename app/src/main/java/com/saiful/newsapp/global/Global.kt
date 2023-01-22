package com.saiful.newsapp.global

import android.view.View
import com.saiful.newsapp.database.NewsArticle

class Global {
    companion object {
        var category: String? = null
        var newsArticle: NewsArticle? = null
        var contextView: View? = null
    }
}