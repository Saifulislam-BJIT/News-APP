package com.saiful.newsapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saiful.newsapp.R
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.viewModel.NewsViewModel

class CardNewsAdapter(
    private val context: Context,
    private val dataset: List<NewsArticle>,
    private val viewModel: NewsViewModel
) : RecyclerView.Adapter<CardNewsAdapter.CardNewsViewHolder>() {

    class CardNewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.card_news_title)
        val newsDescription: TextView = view.findViewById(R.id.card_news_description)
        val newsBookmark: Button = view.findViewById(R.id.card_news_bookmark)
//        val newsContinue: Button = view.findViewById(R.id.card_news_continue_reading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardNewsViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_card, parent, false)
        return CardNewsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CardNewsViewHolder, position: Int) {
        val item = dataset[position]
        holder.newsTitle.text = item.title ?: "----"
//        holder.newsAuthor.text = item.author ?: "----"
//        holder.newsDate.text = item.publishedAt?.substring(0, 10) ?: "----"
        holder.newsDescription.text = item.description ?: "----"
        Glide
            .with(holder.itemView.context)
            .load(item.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.ic_search)
            .into(holder.itemView.findViewById(R.id.card_news_image));

//        details fragment action
//        holder.newsContinue.setOnClickListener {
//            Log.d("TAG", "onBindViewHolder: click")
//            Global.newsArticle = item
//            Log.d("TAG", "onBindViewHolder: ${Global.newsArticle}")
//            it.findNavController().navigate(R.id.newsArticleFragment)
//        }

//        Bookmark button
        holder.newsBookmark.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: click")
            viewModel.addBookmarkNews(item)
        }

//        if(item.isBookmark) {
//            holder.newsBookmark.setImageResource(R.drawable.ic_favorite)
//        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}