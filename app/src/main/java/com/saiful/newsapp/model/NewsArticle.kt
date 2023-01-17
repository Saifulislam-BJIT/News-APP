package com.saiful.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String?,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceName: String?,
    val url: String?,
    val urlToImage: String?
)
