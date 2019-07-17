package br.com.atlas.base.ui.fragment

import android.os.Bundle
import br.com.atlas.base.presenter.PresenterActions
import br.com.atlas.base.view.PresenterView

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
abstract class MVPBaseFragment<in V : PresenterView, P : PresenterActions<V>> : BaseFragment() {

    lateinit var presenter: P

    abstract fun buildPresenter(): P

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = buildPresenter()
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
