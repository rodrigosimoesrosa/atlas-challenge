package br.com.atlas.presenter.news.list.listener

import br.com.atlas.base.reactive.interactor.OnSingleObserver
import br.com.atlas.entity.domain.everything.Article
import br.com.atlas.interactor.everything.NewsInteractor
import br.com.atlas.presenter.news.list.ListNews

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class OnLoadNewsListener(private val view: ListNews.View?) : OnSingleObserver<List<Article>>(),
        NewsInteractor.OnLoadEverything {

    override fun onSuccess(articles: List<Article>) {
        if (articles.isEmpty()) {
            view?.showEmptyList()
            return
        }

        view?.showArticles(articles.map { it.toView() })
    }

    override fun onError(throwable: Throwable) {
        view?.showFailed(throwable)
    }

}
