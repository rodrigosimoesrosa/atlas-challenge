package br.com.atlas.idle

import android.support.test.espresso.IdlingResource
import br.com.atlas.base.reactive.SingleExecutor

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class SingleExecutorIdlingResource: IdlingResource, SingleExecutor.SingleExecutorListener {

    private var idle = true
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return SingleExecutorIdlingResource::class.java.simpleName
    }

    override fun isIdleNow() = idle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    override fun begin() {
        idle = false
    }

    override fun finally() {
        idle = true
        resourceCallback?.onTransitionToIdle()
    }

}
