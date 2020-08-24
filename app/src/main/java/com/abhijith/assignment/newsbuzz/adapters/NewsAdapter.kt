package com.abhijith.assignment.newsbuzz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.assignment.newsbuzz.R
import com.abhijith.assignment.newsbuzz.models.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*


// We are implementing our recyclerview with DiffUtil - calculates the differences between two lists
// and updates only those items. Another advantage is that it happens on background which will not
// block the UI thread

// notifyDataSetChanged - will update the whole recycler view even though the views arent changed
// which is inefficient

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    // object - anonymous class
    private val differCallback = object :DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //return oldItem.id == newItem.id // generally this should suffice but there might be some
            //articles which might not have id.. so we will compare the url

            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    // AsyncList Differ - list differ is a tool that takes two lists and compare them and give us the
    // differences
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text  = article.publishedAt
            setOnClickListener {
                // when the onItemClickListener is not null, we want to call the lambda function as below
                // with the current article
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // takes article as a parameter and returns nothing but a Unit
    // it is nullable so we set it to null by default
    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener :(Article) -> Unit) {
        onItemClickListener = listener
    }
}