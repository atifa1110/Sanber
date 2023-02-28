package com.atifa.sanber

import com.google.gson.annotations.SerializedName

data class NewsItem (
    @field:SerializedName("author")
    val author : String,

    @field:SerializedName("title")
    val title : String,

    @field:SerializedName("url")
    val url : String,

    @field:SerializedName("urlToImage")
    val urlToImage : String,

    @field:SerializedName("publishedAt")
    val publishedAt : String,

    @field:SerializedName("content")
    val content: String,
)

data class ResponseNews (

    @field:SerializedName("status")
    val status : String,

    @field:SerializedName("totalResults")
    val totalResults : Int,

    @field:SerializedName("articles")
    val articles: ArrayList<NewsItem>,
)