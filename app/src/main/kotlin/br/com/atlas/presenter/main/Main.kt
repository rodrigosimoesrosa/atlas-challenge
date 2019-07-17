package br.com.atlas.presenter.main

import android.app.Activity
import br.com.atlas.base.presenter.PresenterActions
import br.com.atlas.base.view.PresenterView

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object Main {

    interface View : PresenterView

    interface Presenter: PresenterActions<View> {
        fun loadNews()
    }

    interface Router<ACTIVITY : Activity> : br.com.atlas.base.router.Router<ACTIVITY> {
        fun showNews()
    }

}