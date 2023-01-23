package com.saiful.newsapp.global

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.saiful.newsapp.database.NewsArticle

class Global {
    companion object {
        var category: String? = null
        var newsArticle: NewsArticle? = null
        @SuppressLint("StaticFieldLeak")
        var contextView: View? = null
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }
}