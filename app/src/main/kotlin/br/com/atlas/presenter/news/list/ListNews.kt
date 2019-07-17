package br.com.atlas.presenter.news.list

import android.app.Activity
import br.com.atlas.base.presenter.PresenterActions
import br.com.atlas.base.view.PresenterView
import br.com.atlas.entity.view.everything.Article

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object ListNews {

    interface View : PresenterView {
        fun showArticles(articles: List<Article>)
        fun showFailed(throwable: Throwable)
        fun showEmptyList()
    }

    interface Presenter : PresenterActions<View> {
        fun loadNews()
        fun showArticleDetail(article: Article)
    }

    interface Router<ACTIVITY : Activity> : br.com.atlas.base.router.Router<ACTIVITY> {
        fun showArticleDetail(article: Article)
    }

}