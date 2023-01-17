package com.saiful.newsapp.ui.fragment.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saiful.newsapp.R
import com.saiful.newsapp.viewModel.NewsViewModel

class BusinessFragment : Fragment() {
private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
//        val viewModel: NewsViewModel by viewModels()
        viewModel.getTopHeadlines()
    }
}