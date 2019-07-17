package br.com.atlas.base.ui.activity

import android.os.Bundle
import br.com.atlas.base.presenter.PresenterActions
import br.com.atlas.base.view.PresenterView
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
abstract class MVPBaseActivity<in VIEW : PresenterView, PRESENTER : PresenterActions<VIEW>> :
    BaseActivity() {

    @Inject
    lateinit var presenter: PRESENTER

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        presenter.attachView(this as VIEW)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
