package com.enseirb.recipefoodapp.services

import okhttp3.*
import java.net.URL

class ApiService {
    var client = OkHttpClient()
    fun GET(url: URL, callback: Callback): Call {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }
}