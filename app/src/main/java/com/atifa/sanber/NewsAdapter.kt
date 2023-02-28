package com.atifa.sanber

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.time.format.DateTimeFormatter

class NewsAdapter(var listNews : ArrayList<NewsItem>)
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val (author, title, url, urlToImage, publishedAt, content) = listNews[position]

        holder.mainHeadline.text = title
        holder.mainContent.text = content
        holder.author.text = author
        holder.publishedAt.text = publishedAt



        Glide.with(holder.itemView.context)
            .load(urlToImage)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .thumbnail(0.5f)
            .into(holder.imageContent);

        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context,DetailActivity::class.java);
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listNews.size

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mainHeadline: TextView = itemView.findViewById(R.id.main_headline)
        var mainContent: TextView = itemView.findViewById(R.id.main_content)
        var imageContent: ImageView = itemView.findViewById(R.id.image_content)
        var author: TextView = itemView.findViewById(R.id.main_author)
        var publishedAt: TextView = itemView.findViewById(R.id.main_publish)
    }
}