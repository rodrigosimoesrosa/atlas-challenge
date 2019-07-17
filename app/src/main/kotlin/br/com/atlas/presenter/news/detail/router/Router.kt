package br.com.atlas.presenter.news.detail.router

import br.com.atlas.extension.showBrowser
import br.com.atlas.presenter.news.detail.NewsDetail
import br.com.atlas.ui.news.detail.NewsDetailActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class Router @Inject constructor() : NewsDetail.Router<NewsDetailActivity> {

    override var activity: NewsDetailActivity? = null

    override fun showBrowser(url: String?) {
        activity?.showBrowser(url)
    }

}
