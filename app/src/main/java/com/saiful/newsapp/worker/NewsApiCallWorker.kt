package com.saiful.newsapp.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class NewsApiCallWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
//        Log.d("TAG", "readFromNews: called worker")
        return if (readFromNews()) {
            // Handle successful response
            Result.success()
        } else {
            // Handle error
            Result.failure()
        }
//        return Result.success()
    }

    private fun readFromNews(): Boolean {
        Log.d("TAG", "readFromNews: called worker")
        return true
    }
}