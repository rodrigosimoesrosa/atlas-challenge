package br.com.atlas.presenter

import br.com.atlas.di.DaggerListNewsComponentForTest
import br.com.atlas.entity.domain.everything.Article
import br.com.atlas.interactor.everything.NewsInteractor
import br.com.atlas.presenter.news.list.ListNews
import br.com.atlas.presenter.news.list.ListNewsPresenter
import br.com.atlas.ui.news.list.ListNewsActivity
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

/**
 * Created by rodrigosimoesrosa on 2019-06-26.
 * Copyright © 2019. All rights reserved.
 */
@RunWith(MockitoJUnitRunner::class)
class ListNewsPresenterTest {

    private lateinit var view: ListNews.View

    private lateinit var router: ListNews.Router<ListNewsActivity>
    private lateinit var interactor: NewsInteractor.LoadEverything

    @Inject lateinit var presenter: ListNews.Presenter

    private val articles = listOf(Article())

    @Before
    fun setup() {
        view = mock()

        DaggerListNewsComponentForTest.builder().build().inject(this)

        router = (presenter as ListNewsPresenter).router
        interactor = (presenter as ListNewsPresenter).loadEverythingInteractor

        presenter.attachView(view)
    }

    @Test
    fun `check loadNews() empty`() {
        val emptyNews = emptyList<Article>()
        doAnswer {
            val callback: NewsInteractor.OnLoadEverything = it.getArgument(5)
            callback.onSuccess(emptyNews)
        }.whenever(interactor).loadEverything(any(), any(), any(), any(), any(), any())

        presenter.loadNews()
        verify(view).showEmptyList()
    }

    @Test
    fun `check loadNews() success`() {
        doAnswer {
            val callback: NewsInteractor.OnLoadEverything = it.getArgument(5)
            callback.onSuccess(articles)
        }.whenever(interactor).loadEverything(any(), any(), any(), any(), any(), any())

        presenter.loadNews()
        verify(view).showArticles(articles.map { it.toView() })
    }

    @Test
    fun `check loadNews() error`() {
        val throwable = Throwable()

        doAnswer {
            val callback: NewsInteractor.OnLoadEverything = it.getArgument(5)
            callback.onError(throwable)
        }.whenever(interactor).loadEverything(any(), any(), any(), any(), any(), any())

        presenter.loadNews()
        verify(view).showFailed(throwable)
    }

    @Test
    fun `check showNewsDetail()`() {
        val article = articles.first().toView()
        presenter.showArticleDetail(article)
        verify(router).showArticleDetail(article)
    }

}
