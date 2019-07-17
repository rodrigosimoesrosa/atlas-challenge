package br.com.atlas.presenter.news.list.router

import br.com.atlas.entity.view.everything.Article
import br.com.atlas.extension.animateFromBottomToCenter
import br.com.atlas.presenter.news.list.ListNews
import br.com.atlas.ui.news.detail.NewsDetailActivity
import br.com.atlas.ui.news.list.ListNewsActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class Router @Inject constructor() : ListNews.Router<ListNewsActivity> {

    override var activity: ListNewsActivity? = null

    override fun showArticleDetail(article: Article) {
        activity?.startActivity(
            classActivity = NewsDetailActivity::class.java,
            data = *arrayOf(article))
        activity?.animateFromBottomToCenter()
    }

}