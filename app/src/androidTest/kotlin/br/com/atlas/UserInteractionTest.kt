package br.com.atlas

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.atlas.idle.ViewShownIdlingResource.Companion.waitViewShown
import br.com.atlas.ui.news.list.ListNewsActivity
import br.com.atlas.ui.news.list.adapter.ListNewsAdapter
import kotlinx.android.synthetic.main.activity_list_news.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UserInteractionTest {

    @get:Rule
    val activityRule = ActivityTestRule(ListNewsActivity::class.java)

    @Test
    fun performOnRecyclerViewItem() {
        waitViewShown(withId(R.id.recyclerViewNews))
        onView(withId(R.id.recyclerViewNews)).perform(RecyclerViewActions.actionOnItemAtPosition<
                ListNewsAdapter.ViewHolder>(0, click()))
    }

    @Test
    fun scrollAndPerformOnRecyclerViewItem() {
        waitViewShown(withId(R.id.recyclerViewNews))
        val lastPosition = activityRule.activity.recyclerViewNews.adapter.itemCount - 1

        onView(withId(R.id.recyclerViewNews)).perform(RecyclerViewActions.scrollToPosition<
                ListNewsAdapter.ViewHolder>(lastPosition))

        onView(withId(R.id.recyclerViewNews)).perform(RecyclerViewActions.actionOnItemAtPosition<
                ListNewsAdapter.ViewHolder>(lastPosition, click()))
    }

    @Test
    fun performOnRecyclerViewItemAndSubmitButtonGoBack() {
        waitViewShown(withId(R.id.recyclerViewNews))
        onView(withId(R.id.recyclerViewNews)).perform(RecyclerViewActions.actionOnItemAtPosition<
                ListNewsAdapter.ViewHolder>(0, click()))

        Espresso.pressBack()
    }

    @Test
    fun performOnRecyclerViewItemAndSubmitButtonBrowser() {
        waitViewShown(withId(R.id.recyclerViewNews))
        onView(withId(R.id.recyclerViewNews)).perform(RecyclerViewActions.actionOnItemAtPosition<
                ListNewsAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.btnOpen)).perform(click())
    }

}