package br.com.atlas.di.module

import br.com.atlas.presenter.news.list.ListNews
import br.com.atlas.presenter.news.list.ListNewsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
class PresenterModuleForTest {

    @Provides
    fun bindLoginPresenter(): ListNews.Presenter {
        val router = RouterModuleForTest.listNewsRouter
        val newsInteractor = InteractorModuleForTest.newInteractor
        val dateInteractor = InteractorModuleForTest.dateInteractor
        val presenter = ListNewsPresenter(newsInteractor, dateInteractor)
        presenter.router = router

        return presenter
    }
}