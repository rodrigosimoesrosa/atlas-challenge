package br.com.atlas.idle

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.ViewFinder
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.Matcher

/**
 * Created by rodrigosimoesrosa on 2019-06-26.
 * Copyright © 2019. All rights reserved.
 */
class ViewShownIdlingResource(private val viewMatcher: Matcher<View>) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun isIdleNow(): Boolean {
        val view = getView(viewMatcher)
        val idle = view == null || view.isShown

        if (idle && resourceCallback != null) {
            resourceCallback?.onTransitionToIdle()
        }

        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    override fun getName(): String {
        return this.toString() + viewMatcher.toString()
    }

    companion object {

        private fun getView(viewMatcher: Matcher<View>): View? {
            return try {
                val viewInteraction = onView(viewMatcher)
                val finderField = viewInteraction.javaClass.getDeclaredField("viewFinder")
                finderField.isAccessible = true
                val finder = finderField.get(viewInteraction) as ViewFinder
                finder.view
            } catch (e: Exception) {
                null
            }
        }

        fun waitViewShown(matcher: Matcher<View>) {
            val idlingResource = ViewShownIdlingResource(matcher)
            try {
                IdlingRegistry.getInstance().register(idlingResource)
                onView(matcher).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            } finally {
                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }
    }
}