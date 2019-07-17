package br.com.atlas.base.router

import android.app.Activity

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
interface Router<ACTIVITY : Activity> {

    var activity: ACTIVITY?

    fun attachActivity(view: ACTIVITY) {
        this.activity = view
    }

    fun unregister() {
        this.activity = null
    }

}
