package com.saiful.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saiful.newsapp.R
import com.saiful.newsapp.database.NewsArticle

class CardNewsAdapter(
    private val context: Context,
    private val dataset: List<NewsArticle>
) : RecyclerView.Adapter<CardNewsAdapter.CardNewsViewHolder>() {

    class CardNewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.card_news_title)
        val newsAuthor: TextView = view.findViewById(R.id.card_news_author)
        val newsDate: TextView = view.findViewById(R.id.card_news_date)
        val newsDescription: TextView = view.findViewById(R.id.card_news_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardNewsViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_card, parent, false)
        return CardNewsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CardNewsViewHolder, position: Int) {
        val item = dataset[position]
        holder.newsTitle.text = item.title ?: "----"
        holder.newsAuthor.text = item.author ?: "----"
        holder.newsDate.text = item.publishedAt?.substring(0, 10) ?: "----"
        holder.newsDescription.text = item.description ?: "----"
        Glide
            .with(holder.itemView.context)
            .load(item.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.ic_search)
            .into(holder.itemView.findViewById(R.id.card_news_image));
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}