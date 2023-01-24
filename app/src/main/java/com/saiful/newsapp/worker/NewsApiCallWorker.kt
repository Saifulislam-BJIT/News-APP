package com.saiful.newsapp.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.saiful.newsapp.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsApiCallWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("TAG", "readFromNews: work manager doWork")
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
                "business",
                "9d68e0d1f113454cb2e1e6ad604ac096"
            )
            Log.d("TAG", "response result: ${response.articles.size}")
        }

        return Result.success()
    }

//    private fun readFromNews(): Boolean {
//        val response = NewsApi.retrofitService.topHeadlinesBusinessNews(
//            "business",
//            "9d68e0d1f113454cb2e1e6ad604ac096"
//        )
//        Log.d("TAG", "response result: ${response.articles.size}")
//        Log.d("TAG", "readFromNews: work manager")
//        return true
//    }
}