package br.com.atlas.ui.news.list.adapter

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.atlas.R
import br.com.atlas.entity.view.everything.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_item_article.*

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object ListNewsAdapter {

    interface ArticleClickListener {
        fun setOnClickListener(article: Article)
    }

    class Build(private val items: ArrayList<Article>,
                private val listener: ArticleClickListener) :
            RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.view_holder_item_article, viewGroup, false)

            return ViewHolder(containerView = view, listener = listener)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.bind(items[position])
        }
    }

    class ViewHolder(override val containerView: View,
                     private val listener: ArticleClickListener) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        private lateinit var article: Article

        init {
            containerView.setOnClickListener { listener.setOnClickListener(article) }
        }

        fun bind(item: Article) {
            article = item
            fillViewHolder()
        }

        private fun fillViewHolder() {
            txtSource.text = article.title
            progressBar.visibility = View.VISIBLE

            val options = RequestOptions()
                .priority(Priority.IMMEDIATE)
                .fallback(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

            Glide.with(itemView.context)
                .load(article.urlToImage)
                .apply(options)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(exception: GlideException?, model: Any?,
                                              target: com.bumptech.glide.request.target.Target<Drawable>,
                                              isFirstResource: Boolean): Boolean {
                        imgArticle.scaleType = ImageView.ScaleType.CENTER_INSIDE
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?,
                                                 target: com.bumptech.glide.request.target.Target<Drawable>,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        imgArticle.scaleType = ImageView.ScaleType.CENTER_INSIDE
                        return false
                    }

                }).into(imgArticle)
        }

    }

}
