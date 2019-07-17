package br.com.atlas.di.module

import br.com.atlas.presenter.main.Main
import br.com.atlas.presenter.main.MainPresenter
import br.com.atlas.presenter.news.detail.NewsDetail
import br.com.atlas.presenter.news.detail.NewsDetailPresenter
import br.com.atlas.presenter.news.list.ListNews
import br.com.atlas.presenter.news.list.ListNewsPresenter
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindMainPresenter(presenter: MainPresenter): Main.Presenter

    @Binds
    abstract fun bindListNews(presenter: ListNewsPresenter): ListNews.Presenter

    @Binds
    abstract fun bindNewsDetail(presenter: NewsDetailPresenter): NewsDetail.Presenter

}