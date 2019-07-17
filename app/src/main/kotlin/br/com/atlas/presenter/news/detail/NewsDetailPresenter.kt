package br.com.atlas.presenter.news.detail

import br.com.atlas.base.presenter.viper.Presenter
import br.com.atlas.extension.byLocale
import br.com.atlas.extension.toISO8601UTC
import br.com.atlas.ui.news.detail.NewsDetailActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class NewsDetailPresenter @Inject constructor() : Presenter<NewsDetail.View, NewsDetailActivity,
        NewsDetail.Router<NewsDetailActivity>>(), NewsDetail.Presenter {

    override fun formatDate(date: String?): String? {
        return date?.toISO8601UTC()?.byLocale()
    }

    override fun showBrowser(url: String?) {
        router.showBrowser(url)
    }

}
