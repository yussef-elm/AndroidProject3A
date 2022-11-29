package com.enseirb.recipefoodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enseirb.recipefoodapp.services.ApiService
import com.enseirb.recipefoodapp.adapters.CategoriesAdapter
import com.enseirb.recipefoodapp.constants.URLs
import com.enseirb.recipefoodapp.models.CategoriesResponse
import java.io.IOException
import java.net.URL
import com.google.gson.Gson
import okhttp3.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var  categoriesAdapter: CategoriesAdapter
    private var apiService = ApiService()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hiddeAppBar()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        getAllCategories()
    }


    fun getAllCategories(){
        val url = URL(URLs.CATEGORIES)
        apiService.GET(url,object: Callback {
            override fun onFailure(call: Call, e : IOException){
                Log.e("OKHttp",e.localizedMessage)
                runOnUiThread {
                    Toast.makeText(this@MainActivity,"Problem on loading categories",Toast.LENGTH_LONG)
                        .show()
                }
            }
            override fun onResponse(call: Call, response : Response){
                response.body?.string()?.let {
                    var gson = Gson();
                    var categoriesResponse = gson.fromJson(it,CategoriesResponse ::class.java)
                    categoriesResponse.categories?.let { item ->
                        runOnUiThread {
                            categoriesAdapter = CategoriesAdapter(this@MainActivity , item)
                            recyclerView.adapter = categoriesAdapter
                            recyclerView.layoutManager = GridLayoutManager(applicationContext,3,LinearLayoutManager.VERTICAL,false)
                        }
                    }
                }
            }
        })
    }


    fun  hiddeAppBar(){
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
    }

}