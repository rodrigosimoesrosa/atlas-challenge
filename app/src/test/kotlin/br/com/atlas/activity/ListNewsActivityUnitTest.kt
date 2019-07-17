package br.com.atlas.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import br.com.atlas.R
import br.com.atlas.ui.news.list.ListNewsActivity
import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@RunWith(RobolectricTestRunner::class)
class ListNewsActivityUnitTest {

    private lateinit var activity: ListNewsActivity
    private lateinit var controller: ActivityController<ListNewsActivity>

    @Before
    fun setUp() {
        controller = Robolectric.buildActivity(ListNewsActivity::class.java)
        activity = controller.create().start().get()
    }

    @Test
    @Throws(Exception::class)
    fun `should not be null`() {
        assertNotNull(activity)
    }

    @Test
    fun `check title text`() {
        val screenTitle = activity.findViewById(R.id.toolBar) as Toolbar
        val correctText = activity.getString(R.string.list_news)

        assertNotNull("Title could not be found", screenTitle)
        assertTrue("Title contains incorrect text",
            correctText == screenTitle.title)
    }

    @Test
    fun `check pause and resume Activity`() {
        controller.pause().resume()
    }

    @Test
    fun `check recreate Activity`() {
        val bundle = Bundle()

        controller
            .saveInstanceState(bundle)
            .pause()
            .stop()
            .destroy()

        controller = Robolectric.buildActivity(ListNewsActivity::class.java)
            .create(bundle)
            .start()
            .restoreInstanceState(bundle)
            .resume()
            .visible()

        activity = controller.get()
    }
}
