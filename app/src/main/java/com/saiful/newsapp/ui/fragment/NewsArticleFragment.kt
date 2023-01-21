package com.saiful.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saiful.newsapp.R
import com.saiful.newsapp.databinding.FragmentNewsArticleBinding
import com.saiful.newsapp.global.Global

class NewsArticleFragment : Fragment() {
    private var _binding: FragmentNewsArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TAG", "onViewCreated: ${Global.newsArticle?.url}")
        binding.goToWeb.text = "Continue reading"
        binding.goToWeb.setOnClickListener {
            val url = Global.newsArticle?.url ?: "https://www.google.com"
            val action = NewsArticleFragmentDirections.actionNewsArticleToWebView(url)
            findNavController().navigate(action)
        }

//        Bottom navigation hide
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.GONE
    }
}