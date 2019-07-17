package br.com.atlas.presenter.main.router

import br.com.atlas.presenter.main.Main
import br.com.atlas.ui.main.MainActivity
import br.com.atlas.ui.news.list.ListNewsActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class Router @Inject constructor() : Main.Router<MainActivity> {

    override var activity: MainActivity? = null

    override fun showNews() {
        activity?.startActivity(ListNewsActivity::class.java)
        activity?.finish()
    }

}