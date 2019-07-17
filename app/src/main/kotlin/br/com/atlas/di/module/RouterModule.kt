package br.com.atlas.di.module

import br.com.atlas.presenter.main.Main
import br.com.atlas.presenter.main.router.Router
import br.com.atlas.presenter.news.detail.NewsDetail
import br.com.atlas.presenter.news.list.ListNews
import br.com.atlas.ui.main.MainActivity
import br.com.atlas.ui.news.detail.NewsDetailActivity
import br.com.atlas.ui.news.list.ListNewsActivity
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
abstract class RouterModule {

    @Binds
    abstract fun bindMainRouter(presenter: Router): Main.Router<MainActivity>

    @Binds
    abstract fun bindListNewsRouter(presenter: br.com.atlas.presenter.news.list.router.Router):
            ListNews.Router<ListNewsActivity>

    @Binds
    abstract fun bindNewsDetailRouter(presenter: br.com.atlas.presenter.news.detail.router.Router):
            NewsDetail.Router<NewsDetailActivity>
}