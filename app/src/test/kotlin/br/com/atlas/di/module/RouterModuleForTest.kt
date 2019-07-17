package br.com.atlas.di.module

import br.com.atlas.presenter.news.list.ListNews
import br.com.atlas.ui.news.list.ListNewsActivity
import com.nhaarman.mockito_kotlin.mock
import dagger.Module

/**
 * Created by rodrigosimoesrosa on 2019-06-26.
 * Copyright © 2019. All rights reserved.
 */
@Module
class RouterModuleForTest {

    companion object {
        val listNewsRouter: ListNews.Router<ListNewsActivity> = mock()
    }

}
