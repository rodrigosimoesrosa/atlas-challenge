package br.com.atlas.presenter.news.list

import br.com.atlas.base.presenter.viper.Presenter
import br.com.atlas.entity.view.everything.Article
import br.com.atlas.interactor.date.DateInteractor
import br.com.atlas.interactor.everything.NewsInteractor
import br.com.atlas.presenter.news.list.listener.OnLoadNewsListener
import br.com.atlas.ui.news.list.ListNewsActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class ListNewsPresenter @Inject constructor(
        val loadEverythingInteractor: NewsInteractor.LoadEverything,
        val loadDateInteractor: DateInteractor) : Presenter<ListNews.View,
        ListNewsActivity, ListNews.Router<ListNewsActivity>>(), ListNews.Presenter {

    object Values {
        const val KEYWORD = "crypto AND (cryptocurrency OR ethereum OR litecoin OR bitcoin)"

        /**
         * You can use all languages described on https://newsapi.org/docs/endpoints/everything
         * ar de en es fr he it nl no pt ru se ud zh
         * Unfortunately doesn't have pt-BR :(
         */
        const val LANGUAGE = "en"

        /**
         * To request more than 100 result, the API plan must be upgraded
         */
        const val TOTAL = 100


        const val MINUS_DAYS = -20
    }

    init {
        addInteractor(loadEverythingInteractor)
    }

    override fun showArticleDetail(article: Article) {
        router.showArticleDetail(article)
    }

    override fun loadNews() {
        val fromDate = loadDateInteractor.getFrom(Values.MINUS_DAYS)
        val toDate = loadDateInteractor.getTo()

        loadEverythingInteractor.loadEverything(
                keyword = Values.KEYWORD,
                total = Values.TOTAL,
                fromDate = fromDate,
                toDate = toDate,
                language = Values.LANGUAGE,
                listener = OnLoadNewsListener(view))
    }

}
