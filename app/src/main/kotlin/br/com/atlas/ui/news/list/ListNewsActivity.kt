package br.com.atlas.ui.news.list

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import br.com.atlas.R
import br.com.atlas.base.ui.activity.MVPBaseActivity
import br.com.atlas.entity.view.everything.Article
import br.com.atlas.presenter.news.list.ListNews
import br.com.atlas.presenter.news.list.ListNewsPresenter
import br.com.atlas.ui.news.list.adapter.ListNewsAdapter
import br.com.atlas.ui.view.adapter.OnPreDrawItemListener
import kotlinx.android.synthetic.main.activity_list_news.*

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class ListNewsActivity: MVPBaseActivity<ListNews.View, ListNews.Presenter>(), ListNews.View,
        ListNewsAdapter.ArticleClickListener {

    override fun getLayout(): Int = R.layout.activity_list_news

    object Values {
        const val FIRST_FRAME = 0f
        const val LAST_FRAME = 85

        const val COLUMNS = 2
        const val SPACE_BETWEEN_ITEMS = 4
    }

    private var adapter: ListNewsAdapter.Build? = null
    private var articles: ArrayList<Article> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lblTryAgain.setOnClickListener { loadNews() }

        setToolBar()
        loadNews()
    }

    override fun showArticles(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)

        showList(true)
        showEmptyState(false)
        showProgress(false)

        val layoutManager = StaggeredGridLayoutManager(Values.COLUMNS,
                StaggeredGridLayoutManager.VERTICAL)

        val animator = recyclerViewNews.itemAnimator
        if (animator is DefaultItemAnimator) {
            animator.supportsChangeAnimations = false
        }

        adapter = ListNewsAdapter.Build(items = this.articles, listener = this)

        recyclerViewNews.layoutManager = layoutManager
        recyclerViewNews.setHasFixedSize(true)
        recyclerViewNews.setItemViewCacheSize(ListNewsPresenter.Values.TOTAL)
        recyclerViewNews.isDrawingCacheEnabled = true
        recyclerViewNews.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH

        recyclerViewNews.adapter = adapter
        recyclerViewNews.viewTreeObserver.addOnPreDrawListener(OnPreDrawItemListener(recyclerViewNews))

        recyclerViewNews.addItemDecoration(object: RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect, view: View,
                                        parent: RecyclerView, state: RecyclerView.State) {

                if (parent.getChildAdapterPosition(view) == 0) {
                    return
                }

                outRect.left = Values.SPACE_BETWEEN_ITEMS
                outRect.right = Values.SPACE_BETWEEN_ITEMS
                outRect.bottom = Values.SPACE_BETWEEN_ITEMS
                outRect.top = Values.SPACE_BETWEEN_ITEMS
            }
        })
    }

    private fun setToolBar() {
        setSupportActionBar(toolBar)
    }

    private fun loadNews() {
        showList(false)
        showEmptyState(false)
        showProgress(true)

        presenter.loadNews()
    }

    private fun showList(visible: Boolean) {
        if (visible) {
            recyclerViewNews.visibility = View.VISIBLE
            return
        }

        recyclerViewNews.visibility = View.GONE
    }

    private fun showEmptyState(visible: Boolean) {
        if (visible) {
            emptyStateView.visibility = View.VISIBLE

            lottieAnimationError.progress = Values.FIRST_FRAME
            lottieAnimationError.setMaxFrame(Values.LAST_FRAME)
            lottieAnimationError.playAnimation()
            return
        }

        emptyStateView.visibility = View.GONE
    }

    private fun showProgress(visible: Boolean) {
        if (visible) {
            progressBar.visibility = View.VISIBLE
            return
        }

        progressBar.visibility = View.GONE
    }

    override fun setOnClickListener(article: Article) {
        presenter.showArticleDetail(article)
    }

    override fun showFailed(throwable: Throwable) {
        val message = throwable.message ?: getString(R.string.error_generic_message)
        showSnack(view = linearLayout, message = message, color = R.color.snackBar)

        showEmptyState(true)
        showProgress(false)
        showList(false)
    }

    override fun showEmptyList() {
        showFailed(Throwable(getString(R.string.list_news_empty_list)))
    }
}
