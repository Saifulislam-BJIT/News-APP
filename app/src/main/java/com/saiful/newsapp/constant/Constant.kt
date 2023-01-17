package com.saiful.newsapp.constant

import android.app.Application
import com.saiful.newsapp.R

class Constant {
    companion object {
        val TOKEN = Application().applicationContext.getString(R.string.api_key)
    }
}