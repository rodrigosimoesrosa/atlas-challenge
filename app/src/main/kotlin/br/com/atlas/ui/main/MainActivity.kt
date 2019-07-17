package br.com.atlas.ui.main

import br.com.atlas.R
import br.com.atlas.base.ui.activity.MVPBaseActivity
import br.com.atlas.presenter.main.Main

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class MainActivity : MVPBaseActivity<Main.View, Main.Presenter>(), Main.View {

    override fun getLayout(): Int = R.layout.activity_main

    override fun onResume() {
        super.onResume()
        presenter.loadNews()
    }

}
