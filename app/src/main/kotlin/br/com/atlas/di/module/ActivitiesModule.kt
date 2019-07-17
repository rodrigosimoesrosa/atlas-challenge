package br.com.atlas.di.module

import br.com.atlas.ui.main.MainActivity
import br.com.atlas.ui.news.detail.NewsDetailActivity
import br.com.atlas.ui.news.list.ListNewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
interface ActivitiesModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeListNewsActivity(): ListNewsActivity

    @ContributesAndroidInjector
    fun contributeNewsDetailActivity(): NewsDetailActivity

}