package com.saiful.newsapp.ui.fragment.tab

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saiful.newsapp.adapter.CardNewsAdapter
import com.saiful.newsapp.databinding.FragmentBusinessBinding
import com.saiful.newsapp.global.Global
import com.saiful.newsapp.viewModel.NewsViewModel

class BusinessFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        Global.category = "business"
        viewModel.readAllNewsFromLocal()

        val recycler = binding.cardNewsRecycler
        recycler.setHasFixedSize(true)
        viewModel.readAllNews.observe(viewLifecycleOwner) {
            Log.d("TAG", "onResume:  ${it.size}")
            if (it.isEmpty()) {
                viewModel.loadNewsFromRemote()
                recycler.adapter?.notifyDataSetChanged()
            }
            recycler.adapter = CardNewsAdapter(requireContext(), it)
        }
    }
}