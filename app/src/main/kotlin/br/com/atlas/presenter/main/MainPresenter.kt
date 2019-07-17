package br.com.atlas.presenter.main

import br.com.atlas.base.presenter.viper.Presenter
import br.com.atlas.ui.main.MainActivity
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class MainPresenter @Inject constructor() : Presenter<Main.View, MainActivity,
        Main.Router<MainActivity>>(), Main.Presenter {

    object Values {
        const val FAKE_LOADING = 2000L
    }

    override fun loadNews() {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() { router.showNews() } },
            Values.FAKE_LOADING
        )
    }

}