package br.com.atlas.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri
import br.com.atlas.R

/**
 * Created by rodrigosimoesrosa on 30/01/18.
 * Copyright © 2019. All rights reserved.
 */
fun Activity.showBrowser(url: String?) {
    if (!url.isNullOrEmpty()) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}

fun Activity.animateFromBottomToCenter() {
    this.overridePendingTransition(R.anim.down_center, R.anim.nothing)
}

fun Activity.animateFromCenterToBottom() {
    this.overridePendingTransition(R.anim.nothing, R.anim.center_down)
}
