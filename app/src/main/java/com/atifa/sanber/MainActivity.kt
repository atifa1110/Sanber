package com.atifa.sanber

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainActivity : AppCompatActivity(){

    private val apiKey : String = "36002c7e54a04afe84c4dd57c0e5711b"
    private val country : String = "id"
    private val TAG : String = "MainActivity"
    private lateinit var rvNews : RecyclerView
    private lateinit var progressBar: ProgressBar
    private val listNews = ArrayList<NewsItem>()
    private lateinit var newsAdapter : NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNews = findViewById(R.id.rv_news)
        rvNews.setHasFixedSize(true)
        newsAdapter = NewsAdapter(listNews)
        showRecyclerList()
        findNews()
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvNews.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvNews.layoutManager = LinearLayoutManager(this)
        }
        rvNews.adapter = newsAdapter
    }


    private fun findNews(){
        val client = ApiConfig.getApiService().getNews(country,30,apiKey)
        client.enqueue(object : Callback<ResponseNews> {
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    response.body()?.let { listNews.addAll(it.articles) }
                    newsAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Berhasil")
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}