package com.saiful.newsapp.ui.fragment.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.saiful.newsapp.R
import com.saiful.newsapp.database.NewsArticle
import com.saiful.newsapp.global.Global
import com.saiful.newsapp.viewModel.NewsViewModel

class BusinessFragment : Fragment() {
    private val viewModel: NewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getTopHeadlines()
//        Global.category = "business"
//        viewModel.getTopHeadlines()
    }

    override fun onResume() {
        super.onResume()
        Global.category = "business"
        viewModel.getTopHeadlines()
    }
}