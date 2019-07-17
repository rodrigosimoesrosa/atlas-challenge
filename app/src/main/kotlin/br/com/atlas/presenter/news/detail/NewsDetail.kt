package br.com.atlas.presenter.news.detail

import android.app.Activity
import br.com.atlas.base.presenter.PresenterActions
import br.com.atlas.base.view.PresenterView

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object NewsDetail {

    interface View : PresenterView

    interface Presenter : PresenterActions<View> {
        fun showBrowser(url: String?)
        fun formatDate(date: String?): String?
    }

    interface Router<ACTIVITY : Activity> : br.com.atlas.base.router.Router<ACTIVITY> {
        fun showBrowser(url: String?)
    }

}