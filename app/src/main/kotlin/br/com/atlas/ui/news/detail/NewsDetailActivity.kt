package br.com.atlas.ui.news.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import br.com.atlas.R
import br.com.atlas.base.ui.activity.MVPBaseActivity
import br.com.atlas.entity.view.everything.Article
import br.com.atlas.extension.animateFromCenterToBottom
import br.com.atlas.presenter.news.detail.NewsDetail
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.news_detail_content.*

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class NewsDetailActivity : MVPBaseActivity<NewsDetail.View, NewsDetail.Presenter>(),
    NewsDetail.View {

    override fun getLayout(): Int = R.layout.activity_news_detail

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnOpen.setOnClickListener { presenter.showBrowser(article?.url) }

        checkSavedInstanceState(savedInstanceState)
        setupToolbar()

        setImageHeader()
        setArticle()
    }

    private fun setArticle() {
        txtTitle.text = article?.title
        txtDescription.text = article?.description
        txtPublishedAt.text = presenter.formatDate(article?.publishedAt)
        txtAuthor.text = article?.author
        txtContent.text = article?.content
    }

    private fun setImageHeader() {
        progressBar.visibility = View.VISIBLE

        val options = RequestOptions()
                .priority(Priority.IMMEDIATE)
                .fallback(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(this)
                .load(article?.urlToImage)
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
                        imgArticle.scaleType = ImageView.ScaleType.CENTER_CROP
                        progressBar.visibility = View.GONE
                        return false
                    }

                }).into(imgArticle)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collapsingToolbar.title = getTitleValue()
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }

                if (scrollRange + verticalOffset == 0) {
                    showCollapsingToolbarTitle()
                    isShow = true
                    return
                }

                if (isShow) {
                    showTitleInsideToolbar()
                    isShow = false
                }
            }
        })
    }

    private fun showCollapsingToolbarTitle() {
        collapsingToolbar.title = getTitleValue()
        txtSource.visibility = View.GONE
    }

    private fun showTitleInsideToolbar() {
        collapsingToolbar.title = " "
        val value = getTitleValue()
        if (value.isNullOrEmpty()) {
            txtSource.visibility = View.GONE
            return
        }

        txtSource.visibility = View.VISIBLE
        txtSource.text = value
    }

    private fun getTitleValue(): String? {
        return article?.source?.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable(Values.SERIALIZABLE, article)
        super.onSaveInstanceState(outState)
    }

    override fun finish() {
        super.finish()
        animateFromCenterToBottom()
    }

    private fun checkSavedInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            article = getSerializable()
            return
        }

        article = savedInstanceState.getSerializable(Values.SERIALIZABLE) as Article?
    }
}