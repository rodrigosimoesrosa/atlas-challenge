package br.com.atlas.base.presenter

import br.com.atlas.base.view.PresenterView

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
interface PresenterActions<in V : PresenterView> {
    fun attachView(view: V)
    fun detachView()
}
